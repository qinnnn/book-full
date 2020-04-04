package io.renren.modules.reptile.resources;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.book.entity.BookFictionEntity;
import io.renren.modules.book.entity.BookFictionSourceEntity;
import io.renren.modules.book.entity.BookSourceEntity;
import io.renren.modules.book.service.BookFictionService;
import io.renren.modules.book.service.BookFictionSourceService;
import io.renren.modules.book.service.BookSourceService;
import io.renren.modules.reptile.change.CatalogChange;
import io.renren.modules.reptile.utils.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 书趣阁资源获取方法 小说
 */
@Component
public class ShuQuGeResources {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private JsoupUtil jsoupUtil;
    @Autowired
    private BookFictionService bookFictionService;
    @Autowired
    private BookFictionSourceService bookFictionSourceService;
    @Autowired
    private BookSourceService bookSourceService;

    /**
     *  获取小说目录
     * @param fictionURL
     */
    public List<CatalogChange> getCatalog(String fictionURL) {
        log.info("书趣阁---获取小说目录");
        Document doc = jsoupUtil.getDoc(fictionURL);
        Elements elements = doc.select("dd>a");
        log.info("小说章数：" + elements.size());
        int k = 12;//前面12章节重复
        int sort = 1;//数据库排序
        int j = elements.size() - 12;//前面12章节重复
        String url = fictionURL.split("/index.html")[0];
        List<CatalogChange> catalogChangeList = new ArrayList<>();
        for (int i = 0; i < j; i++) {
            CatalogChange catalogChange = new CatalogChange();
            catalogChange.setTitle(elements.get(k).text());
            catalogChange.setUrl(url+"/"+elements.get(k).attr("href"));
            catalogChange.setSort(sort);
            catalogChangeList.add(catalogChange);
            sort++;
            k++;
        }
        log.info("书趣阁---获取小说目录成功");
        return catalogChangeList;
    }

    /**
     *  书趣阁爬取小说
     */
    @Async
//    @Transactional(rollbackFor = RuntimeException.class)
    public void crawl(BookSourceEntity bookSourceEntity) throws InterruptedException {
        JSONObject dataObject = JSONObject.parseObject(bookSourceEntity.getDateValue());
        log.info("书趣阁---爬取小说信息");
        Integer number = bookSourceEntity.getNumber();
        Boolean state = true;
        while (state){
            number++;
            state = addFiction("http://www.shuquge.com/txt/"+number+"/index.html",bookSourceEntity.getSourceName(),bookSourceEntity.getSourceId());
            if(state){
                bookSourceEntity.setNumber(number);
                bookSourceService.updateById(bookSourceEntity);
            }
            Thread.sleep(300);
        }
        log.info("书趣阁---获取小说信息完成");
    }

    /**
     *  小说查询如果没有则入库，如果有则添加源
     */
//    @Async
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean addFiction(String fictionURL,String sourceName,Long sourceId) {
        Document doc = jsoupUtil.getDoc(fictionURL);
        if(doc==null){
            return false;
        }
        BookFictionEntity bookFictionEntity = getFictions(doc);
        if(bookFictionEntity!=null){
            BookFictionEntity bfe = bookFictionService.getOne(new QueryWrapper<BookFictionEntity>().eq("fiction_name",bookFictionEntity.getFictionName()).last("limit 1"));
            if(bfe==null){ //无此小说 进行新增
                bookFictionEntity.setSourceId(sourceId);
                bookFictionEntity.setSourceName(sourceName);
                bookFictionEntity.setSourceState(1);
                bookFictionEntity.setUpdateTime(new Date());
                bookFictionEntity.setCreateTime(new Date());
                bookFictionService.save(bookFictionEntity);
                BookFictionSourceEntity bookFictionSourceEntity = new BookFictionSourceEntity();
                bookFictionSourceEntity.setFictionId(bookFictionEntity.getFictionId());
                bookFictionSourceEntity.setSourceId(sourceId);
                bookFictionSourceEntity.setNewest(bookFictionEntity.getNewest());
                bookFictionSourceEntity.setState(bookFictionEntity.getState());
                bookFictionSourceEntity.setNumber(bookFictionEntity.getNumber());
                bookFictionSourceEntity.setSourceName(sourceName);
                bookFictionSourceEntity.setUrl(fictionURL);
                bookFictionSourceEntity.setUpdateTime(new Date());
                bookFictionSourceEntity.setCreateTime(new Date());
                bookFictionSourceService.save(bookFictionSourceEntity);
                log.info("书趣阁---成功获取小说:"+bookFictionEntity.getFictionName()+"地址:"+fictionURL);
            }else{ //已有此小说
                BookFictionSourceEntity bookFictionSourceEntity = bookFictionSourceService.getOne(new QueryWrapper<BookFictionSourceEntity>().eq("fiction_id",bfe.getFictionId()).eq("source_id",sourceId).last("limit 1"));
                if(bookFictionSourceEntity==null){ //无绑定此源才创建书的源
                    bookFictionSourceEntity = new BookFictionSourceEntity();
                    bookFictionSourceEntity.setFictionId(bfe.getFictionId());
                    bookFictionSourceEntity.setSourceId(sourceId);
                    bookFictionSourceEntity.setSourceName(sourceName);
                    bookFictionSourceEntity.setState(bookFictionEntity.getState());
                    bookFictionSourceEntity.setNumber(bookFictionEntity.getNumber());
                    bookFictionSourceEntity.setNewest(bookFictionEntity.getNewest());
                    bookFictionSourceEntity.setUrl(fictionURL);
                    bookFictionSourceEntity.setUpdateTime(new Date());
                    bookFictionSourceEntity.setCreateTime(new Date());
                    bookFictionSourceService.save(bookFictionSourceEntity);
                    log.info("书趣阁---成功添加小说源:"+bookFictionEntity.getFictionName()+"地址:"+fictionURL);
                }
            }
            return true;
        }else{
            log.info("书趣阁---获取小说信息失败-地址错误:"+fictionURL);
            return false;
        }
    }

    /**
     *  小说查询是否更新
     *
     */
    @Async
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateFiction(BookFictionEntity fictionEntity,BookFictionSourceEntity fictionSourceEntity) {
        log.info("书趣阁---更新小说");
        Document doc = jsoupUtil.getDoc(fictionSourceEntity.getUrl());
        BookFictionEntity bookFictionEntity = getFictions(doc);
        if(bookFictionEntity!=null){
            if(!fictionSourceEntity.getNewest().equals(bookFictionEntity.getNewest())){ //小说更新
                if(fictionEntity.getSourceId().equals(fictionSourceEntity.getSourceId())){ //源相同的话直接更新
                    bookFictionEntity.setNewest(bookFictionEntity.getNewest());
                    bookFictionEntity.setNumber(bookFictionEntity.getNumber());
                    bookFictionEntity.setUpdateTime(new Date());
                    bookFictionEntity.setState(bookFictionEntity.getState());
                    bookFictionService.updateById(bookFictionEntity);
                }else if(fictionEntity.getSourceState().equals(1)){ //小说并未绑定死源的修改
                    if(fictionEntity.getNumber()<bookFictionEntity.getNumber()){ //如果字数相比多则换默认源
                        bookFictionEntity.setSourceName(fictionSourceEntity.getSourceName());
                        bookFictionEntity.setSourceId(fictionSourceEntity.getSourceId());
                        bookFictionEntity.setNewest(bookFictionEntity.getNewest());
                        bookFictionEntity.setNumber(bookFictionEntity.getNumber());
                        bookFictionEntity.setUpdateTime(new Date());
                        bookFictionEntity.setState(bookFictionEntity.getState());
                        bookFictionService.updateById(bookFictionEntity);
                    }
                }
                fictionSourceEntity.setNumber(bookFictionEntity.getNumber());
                fictionSourceEntity.setNewest(bookFictionEntity.getNewest());
                fictionSourceEntity.setState(bookFictionEntity.getState());
                fictionSourceEntity.setUpdateTime(new Date());
                bookFictionSourceService.updateByIdUpdate(fictionSourceEntity);
            }else{ //小说无更新

            }
            log.info("书趣阁---更新小说信息完成");
        }else{
            log.info("书趣阁---更新小说信息失败-地址错误");
        }
    }

    /**
     * 获取 书趣阁小说基本信息
     *
     * @param document
     * @return BookFictionEntity
     */
    public BookFictionEntity getFictions(Document document) {
        try {
            String fictionName = document.select("meta[property=og:novel:book_name]").attr("content");
            String brief = document.select("meta[property=og:description]").attr("content");
            String author = document.select("meta[property=og:novel:author]").attr("content");
            String type = document.select("meta[property=og:novel:category]").attr("content");
            String newest = document.select("meta[property=og:novel:latest_chapter_name]").attr("content");
            String state = document.select("meta[property=og:novel:status]").attr("content");
            String img = document.select("div>img").attr("abs:src");

            Elements small = document.select("div.small");//小说基本信息
            String number = JsoupUtil.sub(small.get(0).child(3).text());//字数
            Integer states = 1;
            if(state.equals("连载中")){
                states = 1;
            }else{
                states = 2;
            }
            return new BookFictionEntity(fictionName,author,type,states,newest,Integer.valueOf(number),brief,img);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
