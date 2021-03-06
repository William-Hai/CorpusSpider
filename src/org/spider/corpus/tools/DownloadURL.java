package org.spider.corpus.tools;

import java.io.IOException;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spider.corpus.consts.Classify;
import org.spider.corpus.consts.Config;
import org.spider.corpus.consts.Constants;
import org.spider.corpus.queue.SpiderQueue;
import org.utils.naga.files.FileWriteUtils;
import org.utils.naga.filter.BloomFilter;
import org.utils.naga.nums.RandomUtils;
import org.utils.naga.str.StringUtils;
import org.utils.naga.web.impl.WebHTMLParserImpl;
import org.utils.naga.web.poke.HTMLParserUtils;

/**
 * <p>
 * 按照一定的协议将网页地址下载到本地
 * </p>
 * 2015年11月26日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1
 */
public class DownloadURL {

    static final String BASE_ADDRESS = "http://digi.ifeng.com/listpage/817/51/list.shtml?cflag=1&prevCursorId=41511370&cursorId=41511655";
    private static BloomFilter mBloomFilter = null;
    private static final String BASE_URL = BASE_ADDRESS + "";
    private static SpiderQueue mSpiderQueue = null;
    private static final String SUB_PATH = Constants.RAW_PATH + "/" + Classify.Digital.getDesc() + ".txt";
    
    static AtomicInteger urlCount = new AtomicInteger(0);
    private static HTMLParserUtils htmlParser;
    
    static {
        mBloomFilter = new BloomFilter();
        mSpiderQueue = new SpiderQueue();
        
        htmlParser = new HTMLParserUtils(new WebHTMLParserImpl());
    }
    
    public static void main(String[] args) {
        String url = "";
        for (int i = 1; i <= 1; i++) {
            url = BASE_URL + "";
            addDigitalHTMLElements(url);
            
            if (!Config.SystemConfig.DEBUG) {
                downloadHTMLs(SUB_PATH);
            }
        }
    }
    
    // 数码
    private static void addDigitalHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "box650").get(0).children();
            for (Element rootElement : rootElements) {
                String href = rootElement.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 天文科普
    @SuppressWarnings("unused")
    private static void addAstronomyHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "imagelist2").get(0).child(0).children();
            for (Element rootElement : rootElements) {
                String href = BASE_ADDRESS + rootElement.child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 动漫 
    @SuppressWarnings("unused")
    private static void addCartoonHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "imagelist2").get(0).child(0).children();
            for (Element rootElement : rootElements) {
                String href = BASE_ADDRESS + rootElement.child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 留学移民
    @SuppressWarnings("unused")
    private static void addSinaOverseasHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "list_009").get(0).children();
            for (Element rootElement : rootElements) {
                String href = rootElement.child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪教育
    @SuppressWarnings("unused")
    private static void addSinaEducationHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "vido");
            for (Element rootElement : rootElements) {
                String href = rootElement.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 地图交通(等车网)
    @SuppressWarnings("unused")
    private static void addMapHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "txt");
            Elements childrenElements = rootElements.get(0).child(0).children();
            
            boolean firstFlag = true;
            for (Element childrenElement : childrenElements) {
                if (firstFlag) {
                    firstFlag = false;
                    continue;
                }
                
                Elements grandsons = childrenElement.children();
                for (Element element : grandsons) {
                    String href = element.attr("href");
                    if (StringUtils.isEmpty(href)) {
                        continue;
                    }
                    
                    href = BASE_ADDRESS + href;
                    urlCount.incrementAndGet();
                    mSpiderQueue.offer(href);
                    System.out.println("[" + urlCount.get() + "]" + href);
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 地图交通
    @SuppressWarnings("unused")
    private static void addMapHTMLElements2(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "v5_ll_test");
            for (Element rootElement : rootElements) {
                Elements childrenElements = rootElement.child(1).children();
                for (Element childrenElement : childrenElements) {
                    String href = childrenElement.child(0).attr("href");
                    if (StringUtils.isEmpty(href) || !StringUtils.RegexUtils.isWebsiteAddress(href)) {
                        continue;
                    }
                    
                    urlCount.incrementAndGet();
                    mSpiderQueue.offer(href);
                    System.out.println("[" + urlCount.get() + "]" + href);
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 网上购物[奶茶东]
    // http://item.jd.com/1791808238.html
    @SuppressWarnings("unused")
    private static void addJDHTMLElements() {
        int limit = 2000000000;
        BitSet saved = new BitSet(limit);
        String text = "";
        String url = "";
        
        RandomUtils randomUtils = new RandomUtils(limit);
        
        while (true) {
            int random = randomUtils.nextInt();
            if (saved.get(random)) {
                continue;
            }
            
            saved.set(random, true);
            
            url = "http://item.jd.com/" + StringUtils.formatIntegerString(random, "#0000000000") + ".html";
            
            try {
                text = htmlParser.requestHTMLToString(url, 30000, false);
                if (StringUtils.RegexUtils.isSub(text, "<title>302 Found</title>")) {
                    continue;
                }
                
                text = htmlParser.requestHTMLText(url, 30000, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if (StringUtils.isEmpty(text)) {
                continue;
            }
            
            urlCount.incrementAndGet();
            mSpiderQueue.offer(url);
            System.out.println("[" + urlCount.get() + "]" + url);
            
            if (urlCount.get() == 1000) {
                break;
            }
        }
    }
    
    // 技术培训
    @SuppressWarnings("unused")
    private static void addTainningHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "studentS_con_play");
            for (Element rootElement : rootElements) {
                String href = rootElement.attr("href");
                if (StringUtils.isEmpty(href) || !StringUtils.RegexUtils.isWebsiteAddress(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 银行储蓄[ICBC]
    @SuppressWarnings("unused")
    private static void addICBCBankHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "textlb");
            for (Element rootElement : rootElements) {
                String href = BASE_ADDRESS + rootElement.attr("href");
                if (StringUtils.isEmpty(href) || StringUtils.RegexUtils.isSub(href, "default")) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 优酷视频
    @SuppressWarnings("unused")
    private static void addVideoHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "p-link");
            for (Element rootElement : rootElements) {
                String href = rootElement.child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 音乐歌曲[音悦台]
    @SuppressWarnings("unused")
    private static void addMuiscHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "clearfix");
            Elements childrenElements = rootElements.get(0).children();
            for (Element childrenElement : childrenElements) {
                String href = childrenElement.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪音乐
    @SuppressWarnings("unused")
    private static void addSinaMuiscHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "latest_songs");
            Elements childrenElements = rootElements.get(0).child(0).children();
            for (Element childrenElement : childrenElements) {
                String href = childrenElement.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 政府网站
    @SuppressWarnings("unused")
    private static void addGovernmentHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "info");
            for (Element rootElement : rootElements) {
                Elements childrenElements = rootElement.children();
                String href = childrenElements.get(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 资源素材
    @SuppressWarnings("unused")
    private static void addMaterialHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements = document.getElementsByAttributeValue("class", "text_left");
            Elements childrenElements = rootElements.get(0).child(1).children();
            for (Element childrenElement : childrenElements) {
                if (childrenElement.childNodeSize() == 0) {
                    continue;
                }
                
                String href = BASE_ADDRESS + childrenElement.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                mSpiderQueue.offer(href);
                urlCount.incrementAndGet();
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 酒店住宿[艺龙]
    @SuppressWarnings("unused")
    private static void addHotelHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            urlCount.incrementAndGet();
            if (StringUtils.isEmpty(document.text())) {
                System.out.println("[" + urlCount.get() + "]");
                return;
            }
            
            mSpiderQueue.offer(url);
            System.out.println("[" + urlCount.get() + "]" + url);
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 星相算命
    @SuppressWarnings("unused")
    private static void addFortuneHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements  = document.getElementsByAttributeValue("class", "e2");
            Elements childrenElements = rootElements.get(0).children();
            for (Element element : childrenElements) {
                String href = BASE_ADDRESS + element.child(0).attr("href");
                mSpiderQueue.offer(href);
                urlCount.incrementAndGet();
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 小说文字[起点中文]
    @SuppressWarnings("unused")
    private static void addQiDianHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements rootElements  = document.getElementsByTag("tbody");
            Elements childrenElements = rootElements.get(0).children();
            
            boolean firstFlag = true;
            for (Element element : childrenElements) {
                if (!firstFlag) {
                    String href = element.child(2).child(0).attr("href");
                    if (StringUtils.isEmpty(href)) {
                        continue;
                    }
                    
                    mSpiderQueue.offer(href);
                    urlCount.incrementAndGet();
                    System.out.println("[" + urlCount.get() + "]" + href);
                }
                
                if (firstFlag) {
                    firstFlag = false;
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 美食烹饪
    @SuppressWarnings("unused")
    private static void addCookHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "ui_list_1 space_box_home get_num");
            Elements childrenElements = titlelnkElements.get(0).child(0).children();
            for (Element element : childrenElements) {
                String href = element.child(0).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                mSpiderQueue.offer(href);
                urlCount.incrementAndGet();
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 电脑硬件
    @SuppressWarnings("unused")
    private static void addComputerHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "Em_2");
            for (Element element : titlelnkElements) {
                Elements childrenElements = element.child(1).children();
                for (Element childrenElement : childrenElements) {
                    String href = childrenElement.child(0).attr("href");
                    if (StringUtils.isEmpty(href)) {
                        continue;
                    }
                    
                    mSpiderQueue.offer(href);
                    urlCount.incrementAndGet();
                    System.out.println("[" + urlCount.get() + "]" + href);
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 会议沙龙 【segmentfault】
    @SuppressWarnings("unused")
    private static void addSalonHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "col-md-3 col-sm-4 col-xs-12");
            for (Element element : titlelnkElements) {
                mSpiderQueue.offer(BASE_ADDRESS + element.child(0).child(0).attr("href"));
                urlCount.incrementAndGet();
                System.out.println("[" + urlCount.get() + "]" + BASE_ADDRESS + element.child(0).child(0).attr("href"));
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 化学
    @SuppressWarnings("unused")
    private static void addChymistryHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "article-list");
            Element childrenElement = titlelnkElements.get(0);
            Elements childrenElements = childrenElement.children();
            for (Element element : childrenElements) {
                String href = element.child(1).child(0).attr("href");
                if (StringUtils.isEmpty(href)) {
                    continue;
                }
                
                urlCount.incrementAndGet();
                mSpiderQueue.offer(href);
                System.out.println("[" + urlCount.get() + "]" + href);
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 法律维权
    @SuppressWarnings("unused")
    private static void addLawHTMLElements(String url) {
        mSpiderQueue.offer(url);
    }
    
    // 暴走漫画
    @SuppressWarnings("unused")
    private static void addBaozouHTMLElements(String url) {
        mSpiderQueue.offer(url);
    }
    
    // 糗百
    @SuppressWarnings("unused")
    private static void addQiuShiBaiKeHTMLElements(String url) {
        mSpiderQueue.offer(url);
    }
    
    // 数盟 http://dataunion.org
    @SuppressWarnings("unused")
    private static void addDataunionHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "excerpt");
            for (Element element : titlelnkElements) {
                mSpiderQueue.offer(element.child(0).child(0).child(0).attr("href"));
                System.out.println(element.child(0).text());
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 博客园 http://www.cnblogs.com/
    @SuppressWarnings("unused")
    private static void addCnblogsHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "titlelnk");
            for (Element element : titlelnkElements) {
                mSpiderQueue.offer(element.attr("href"));
                System.out.println(element.text());
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 火狐新闻
    @SuppressWarnings("unused")
    private static void addFirefoxHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "list_box");
            for (Element element : titlelnkElements) {
                mSpiderQueue.offer(BASE_ADDRESS + element.attr("href"));
                System.out.println(element.child(0).text());
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪天气(省)
    @SuppressWarnings("unused")
    private static void addWeatherSinaHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "wd_piC");
            for (Element element : titlelnkElements) {
                for (Element childElement : element.children()) {
                    addWeatherSinaSubElements(childElement.attr("href"));
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪天气(市)
    private static void addWeatherSinaSubElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "wd_cm_table");
            for (Element element : titlelnkElements) {
                for (Element childElement : element.children()) {
                    Element tmpElement = childElement.child(0).child(0).child(0);
                    mSpiderQueue.offer(tmpElement.attr("href"));
                    System.out.println(tmpElement.attr("href"));
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪房产
    @SuppressWarnings("unused")
    private static void addSinaHouseHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "grid-s5m0e5");
            for (Element element : titlelnkElements) {
                urlCount.incrementAndGet();
                Element tmpElement = element.child(0).child(0).child(0).child(0).child(0);
                mSpiderQueue.offer(tmpElement.attr("href"));
                System.out.println("[" + urlCount.get() + "]" + tmpElement.attr("href"));
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪中医
    @SuppressWarnings("unused")
    private static void addSinaChineseMedicineHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "artList");
            Elements childrenElements = titlelnkElements.get(0).children();
            for (Element element : childrenElements) {
                urlCount.incrementAndGet();
                Element tmpElement = element.child(0).child(0);
                mSpiderQueue.offer(BASE_ADDRESS + tmpElement.attr("href"));
                System.out.println("[" + urlCount.get() + "]" + BASE_ADDRESS + tmpElement.attr("href"));
                System.out.println(tmpElement.text());
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪历史
    @SuppressWarnings("unused")
    private static void addSinaHistoryHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "news-item  img-news-item");
            for (Element element : titlelnkElements) {
                urlCount.incrementAndGet();
                Element tmpElement = element.child(0).child(0);
                mSpiderQueue.offer(tmpElement.attr("href"));
                System.out.println("[" + urlCount.get() + "]" + tmpElement.attr("href"));
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪育儿
    @SuppressWarnings("unused")
    private static void addSinaBabyHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "list_666");
            for (Element element : titlelnkElements) {
                Elements childrenElements = element.children();
                for (Element child : childrenElements) {
                    mSpiderQueue.offer(child.child(0).attr("href"));
                    urlCount.incrementAndGet();
                    System.out.println("[" + urlCount.get() + "]" + child.child(0));
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 新浪游戏
    @SuppressWarnings("unused")
    private static void addSinaGameHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "newslist");
            for (Element element : titlelnkElements) {
                Elements childrenElements = element.child(0).children();
                for (Element child : childrenElements) {
                    mSpiderQueue.offer(child.child(2).attr("href"));
                    urlCount.incrementAndGet();
                    System.out.println("[" + urlCount.get() + "]" + child.child(2).attr("href"));
                }
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    // 彩票
    @SuppressWarnings("unused")
    private static void addLotteryHTMLElements(String url) {
        try {
            Document document = htmlParser.requestHTML(url, 30000);
            Elements titlelnkElements = document.getElementsByAttributeValue("class", "contlist");
            Elements childrenElements = titlelnkElements.get(0).child(1).children();
            for (Element element : childrenElements) {
                if (element.childNodeSize() == 0) {
                    continue;
                }
                
                mSpiderQueue.offer(BASE_ADDRESS + element.child(1).attr("href"));
                urlCount.incrementAndGet();
                System.out.println("[" + urlCount.get() + "]" + BASE_ADDRESS + element.child(1).attr("href"));
            }
        } catch (IOException e) {
            System.err.println("addElements:" + url);
            e.printStackTrace();
        }
    }
    
    private static void downloadHTMLs(String downloadPath) {
        String currentURL = "";
        StringBuffer buffer = new StringBuffer();
        while(!mSpiderQueue.isQueueEmpty()) {
            currentURL = mSpiderQueue.poll();
            if (!mBloomFilter.contains(currentURL)) {
                buffer.append(currentURL + "\n");
                mBloomFilter.add(currentURL);
            } else {
                System.err.println("----------------- URL已存在 --------------------");
            }
            
            if (buffer.length() >= 1000) {
                writeURLs(downloadPath, buffer.toString());
                buffer.setLength(0);
            }
        }
        
        writeURLs(downloadPath, buffer.toString());
    }
    
    private static void writeURLs(String path, String content) {
        try {
            FileWriteUtils.appendFile(path, content);
        } catch (IOException e) {
            System.err.println("downloadHTML:" + content);
            e.printStackTrace();
        }
    }
}
