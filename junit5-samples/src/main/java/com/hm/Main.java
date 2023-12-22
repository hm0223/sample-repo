package com.hm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jsoup parser samples
 * 
 * @author huwenfeng
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 输入HTML字符串  
        //language=HTML
        String html = "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <p style=\"text-align: center;\" data-mpa-powered-by=\"yiban.io\"><img class=\"rich_pages wxw-img\" data-backh=\"68\" data-backw=\"578\" data-galleryid=\"\" data-imgfileid=\"505702277\" data-ratio=\"0.1175925925925926\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCvAuOqnz4DT6UzzRXOQ2xq5Lg0pUukIa37kNtpicDEziaH5zWDd7ZPMFWib84LWqFMM9PGOQibibvvklDA/640?wx_fmt=png\" data-type=\"png\" data-w=\"1080\" style=\"width: 100%;height: auto;\"></p> \n" +
                "  <section style=\"background-color: rgb(251, 240, 228);color: rgb(62, 62, 62);letter-spacing: 1px;line-height: 1.6;font-size: 16px;\"> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "     <section style=\"text-align: center;line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"vertical-align: middle;display: inline-block;line-height: 0;\"> \n" +
                "       <img class=\"rich_pages wxw-img\" data-imgfileid=\"505702278\" data-ratio=\"0.28691275167785235\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRezmoUsJw9PtD7fmyRyE3w6lAGb1MqiaUuSPicdjJQEHA5xE0ynmicXhMg/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"596\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "     <section style=\"text-align: center;font-size: 18px;\" powered-by=\"xiumi.us\"> \n" +
                "      <p><strong>最新毕业生岗位集锦</strong></p> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "     <section style=\"margin-top: 15px;margin-bottom: 15px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"text-align: center;\"> \n" +
                "       <p>2023进入倒计时</p> \n" +
                "       <p>求职的小伙伴</p> \n" +
                "       <p>获得心仪offer了吗？</p> \n" +
                "       <p>这里！</p> \n" +
                "       <p><strong>定期更新</strong>的</p> \n" +
                "       <p><strong>毕业生岗位需求</strong></p> \n" +
                "       <p>“职”等有缘人</p> \n" +
                "       <p>有意者，速投简历</p> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "     <section style=\"margin-bottom: 20px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"text-align: justify;\"> \n" +
                "       <p style=\"text-align: center;text-wrap: wrap;\">通过<strong>详情页链接</strong></p> \n" +
                "       <p style=\"text-align: center;text-wrap: wrap;\"><strong>联系用人单位</strong></p> \n" +
                "       <p style=\"text-align: center;text-wrap: wrap;\"><br></p> \n" +
                "       <section class=\"mp_common_sticker_iframe_wrp\"> \n" +
                "        <mp-common-sticker class=\"js_uneditable custom_select_card mp_common_sticker_iframe\" data-pluginname=\"emotion\" data-id=\"d1dbef0f3bcdbdaf0e38bfd638c1eaac\" data-type=\"0\" data-emoji_url=\"https://mmbiz.qpic.cn/sz_mmbiz_png/YS1micSNHpRn4pOKAa9rZHcsGnynQAQV8os1pCknyCrP59ooBn0LiaeF4QNcgpICYsDP0hgSFex07526c4NQV9sQ/0\" data-md5=\"d1dbef0f3bcdbdaf0e38bfd638c1eaac\" data-inserted=\"0\" data-is_ban=\"0\" data-loading=\"0\"></mp-common-sticker> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"display: flex;width: 100%;flex-flow: column;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"z-index: 1;\" powered-by=\"xiumi.us\"> \n" +
                "       <section style=\"text-align: center;justify-content: center;display: flex;flex-flow: row;margin-bottom: 10px;\"> \n" +
                "        <section style=\"display: inline-block;width: auto;vertical-align: middle;align-self: center;flex: 0 0 auto;background-color: rgb(216, 154, 141);min-width: 5%;height: auto;padding: 6px 12px;border-style: double;border-width: 6px;border-color: rgb(255, 255, 255);\"> \n" +
                "         <section style=\"text-align: justify;color: rgb(255, 255, 255);\" powered-by=\"xiumi.us\"> \n" +
                "          <p style=\"text-wrap: wrap;\"><strong>毕业生岗位需求</strong></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"text-align: center;justify-content: center;display: flex;flex-flow: row;margin-top: -35px;margin-bottom: 15px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"display: inline-block;width: 96%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;height: auto;border-style: solid;border-width: 2px;border-color: rgb(216, 154, 141);padding: 50px 20px 15px;\"> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">01</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：射频工程师（应届生）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门骐俊物联科技股份有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：电磁场与微波技术</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、带薪年假、年终分红、加班补贴、节日福利、定期体检、包吃</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2998447</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">02</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：税审实习生</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门嘉法税务师事务所有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2261311</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">03</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：芯片工程师</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：朗明纳斯光电（厦门）有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、出差补贴、定期体检、包吃、包住</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2906418</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">04</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：外贸业务员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：中国新兴厦门进出口有限责任公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、带薪年假、绩效奖金、出差补贴、高温补贴、节日福利、专业培训、定期体检、包住</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=1875193</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">05</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：支持计划专员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门海辰储能科技股份有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市同安区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、住房补贴、餐饮补贴、节日福利、专业培训、免费班车、包吃、包住</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2935270</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">06</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：采购助理</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门伟盟环保材料有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市同安区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、商业保险、带薪年假、节日福利、定期体检、员工旅游、每年多次调薪、全勤奖、包吃、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=1183235</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">07</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：模具设计学徒</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门市丰进模具工业有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市集美区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、年终双薪、绩效奖金、加班补贴、餐饮补贴、专业培训、全勤奖、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2909135</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">08</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：招商专员（商业广场）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门嘉盛集团有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2653778</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">09</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：外贸文员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门飞羚纺织服装有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市集美区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、加班补贴、节日福利、员工旅游、全勤奖、包住、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2827268</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">10</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：副总助理</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门网拓科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、加班补贴、高温补贴、节日福利、不加班</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3001250</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">11</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：房建技术员（实习生）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门金九州建设有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、绩效奖金、高温补贴、节日福利、包吃、包住</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2984850</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">12</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：绘图、制图员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门振铨钨钢科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市集美区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、全勤奖</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2917650</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">13</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：电商美工</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门元尊生物工程有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市海沧区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、年终双薪、节日福利、包住</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2721220</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">14</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：预算实习生</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门市永欣胜工程管理咨询有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2998973</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">15</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：Unity 前端工程师（2024届）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门欧米克网络科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、绩效奖金、节日福利、专业培训</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3007771</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">16</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：出纳</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门智科联系统科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市海沧区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：不限</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、年终双薪、节日福利、专业培训、不加班、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2994197</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">17</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：欧洲站运营（德、法、意、西）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：万续通（厦门）贸易有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、绩效奖金、加班补贴、住房补贴、节日福利、专业培训、员工旅游</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2993317</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">18</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：实习生</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：中闽在线（厦门）电子商务有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、加班补贴、餐饮补贴、全勤奖</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2694511</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">19</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：文秘品宣岗</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：中国大地财产保险股份有限公司厦门分公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：有相关工作经验一年以上者或文学类、新闻类、文秘类、财税类等相关专业的优先。中共党员优先。</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、带薪年假、绩效奖金、餐饮补贴、节日福利、专业培训、定期体检</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=1461942</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">20</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：茶饮辅导员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门快乐番薯股份有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、年终双薪、绩效奖金、年终分红、节日福利、专业培训</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2998689</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">21</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：储备干部</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门宏钛盛电子科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市翔安区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、绩效奖金、加班补贴、餐饮补贴、节日福利、每年多次调薪、全勤奖</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3009407</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">22</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：应届汽车专业储备干部</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门盛元集团有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：汽车检测与维修/车辆工程/汽车服务与营销</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、年终双薪、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=949563</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">23</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：招商岗（管培生）</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：星盛深南（厦门）商业管理有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、绩效奖金、交通补贴、出差补贴、餐饮补贴、高温补贴、节日福利、专业培训、定期体检、每年多次调薪</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3006716</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">24</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：财务管理</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：清海浪（厦门）进出口有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市集美区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2986908</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">25</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：管培生</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门扬森数控设备有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市海沧区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：机械/数控/自动化/电气</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、节日福利、包吃、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3006128</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">26</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：应届预算</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：中清睿（厦门）环境科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：工程造价</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、商业保险、带薪年假、年终双薪、绩效奖金、餐饮补贴、高温补贴、节日福利、全勤奖、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2991959</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">27</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：法务专员/助理</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门闽篮超市有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市翔安区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、绩效奖金、住房补贴、高温补贴、节日福利、每年多次调薪、包住、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2982316</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">28</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：电商客服</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：上海弘升光学眼镜有限公司厦门分公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2412480</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">29</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：IE工程师</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门睿康科技有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市集美区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、带薪年假、绩效奖金、加班补贴、住房补贴、餐饮补贴、高温补贴、节日福利、专业培训、员工旅游、全勤奖</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2746378</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">30</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：客户经理助理</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门弘有信信息技术服务有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、商业保险、带薪年假、餐饮补贴、专业培训、免费班车</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2895062</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">31</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：前台客服</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门育龙文化艺术有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、节日福利、专业培训、员工旅游、出国机会、每年多次调薪、全勤奖、工作制服</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2350073</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">32</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：疫苗质量QC</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门万泰沧海生物技术有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市海沧区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：本科</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、餐饮补贴、高温补贴、节日福利、免费班车、定期体检</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3006854</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">33</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：工商注册专员</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：金代账（厦门）财务管理有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市湖里区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、带薪年假、绩效奖金、节日福利、专业培训、每年多次调薪、全勤奖</p> \n" +
                "         <p style=\"text-wrap: wrap;text-align: left;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=2215040</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">34</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：会计实习生</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门兴万财税事务所有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市思明区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：大专</p> \n" +
                "         <p style=\"text-wrap: wrap;\">专业要求：财务会计</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、年终双薪、绩效奖金、通讯补贴、专业培训、每年多次调薪、全勤奖</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=1285167</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "         <p style=\"text-wrap: wrap;\"><br></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: right;justify-content: flex-end;display: flex;flex-flow: row;margin-top: -10px;transform: rotateY(180deg);\"> \n" +
                "         <section style=\"display: inline-block;width: auto;vertical-align: top;align-self: flex-start;flex: 0 0 auto;min-width: 5%;height: auto;border-style: double;border-width: 0px 0px 1px;border-bottom-color: rgb(62, 62, 62);\"> \n" +
                "          <section style=\"transform: perspective(0px);transform-style: flat;\" powered-by=\"xiumi.us\"> \n" +
                "           <section style=\"transform: rotateY(180deg);\"> \n" +
                "            <section style=\"text-align: left;letter-spacing: 2px;font-family: PingFangSC-light;\"> \n" +
                "             <p><span style=\"font-size: 14px;\">▷▷</span><strong><span style=\"font-size: 18px;\">35</span></strong></p> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"margin-top: 10px;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"text-align: justify;letter-spacing: 0px;\"> \n" +
                "         <p style=\"text-wrap: wrap;\"><strong>招聘职位：光学工程师</strong></p> \n" +
                "         <p style=\"text-wrap: wrap;\">招聘单位：厦门金诺花科学仪器有限公司</p> \n" +
                "         <p style=\"text-wrap: wrap;\">所在地区：厦门市翔安区</p> \n" +
                "         <p style=\"text-wrap: wrap;\">学历要求：硕士研究生</p> \n" +
                "         <p style=\"text-wrap: wrap;\">薪资福利：五险、住房公积金、带薪年假、年终双薪、绩效奖金、交通补贴、出差补贴、餐饮补贴、节日福利、专业培训、弹性工作、定期体检</p> \n" +
                "         <p style=\"text-align: left;text-wrap: wrap;\">详情页：<span style='color:red;' class='errorLink'>http://www.xmrc.com.cn/net/info/showco.aspx?id=3008283</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span></p> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"text-align: center;justify-content: center;display: flex;flex-flow: row;margin-bottom: 15px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"display: inline-block;width: 94%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;height: auto;\"> \n" +
                "       <section style=\"text-align: justify;letter-spacing: 0px;\" powered-by=\"xiumi.us\"> \n" +
                "        <p style=\"text-wrap: wrap;\">具体岗位需求以厦门人才网（<span style='color:red;' class='errorLink'>https://www.xmrc.com.cn/</span><span style='color:#90a4ff;'>【链接类型：无效链接】</span>）实时发布为准，更多岗位详情敬请关注厦门人才网官网。</p> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "  </section> \n" +
                "  <p style=\"text-align: center;\"><br></p> \n" +
                "  <section data-role=\"outer\" label=\"edit by 135editor\"> \n" +
                "   <section data-tools=\"135编辑器\" data-id=\"98902\"> \n" +
                "    <section data-role=\"layout\" style=\"display: flex;justify-content: center;flex-flow: row;\"> \n" +
                "     <section data-role=\"layout\" data-width=\"96%\" style=\"display: inline-block;width: 96%;line-height: 2;vertical-align: middle;align-self: center;max-width: 96% !important;\"> \n" +
                "      <section data-role=\"layout-inner\"> \n" +
                "       <section data-role=\"paragraph\"> \n" +
                "        <section data-tools=\"135编辑器\" data-id=\"116350\" data-width=\"100%\" style=\"margin-right: auto;margin-left: auto;width: 100%;flex: 0 0 100%;\"> \n" +
                "         <section style=\"margin: 20px auto;\"> \n" +
                "          <section> \n" +
                "           <section style=\"display: flex;justify-content: center;\"> \n" +
                "            <section style=\"padding-right: 4px;padding-left: 4px;background-color: rgb(255, 255, 255);\"> \n" +
                "             <section style=\"padding: 3px 7px;background-color: rgb(0, 0, 0);font-size: 12px;color: rgb(255, 255, 255);text-align: center;\"> \n" +
                "              <span style=\"font-size: 16px;letter-spacing: 2.5px;\"><strong>往期回顾</strong></span> \n" +
                "             </section> \n" +
                "             <section data-width=\"80%\" style=\"margin: -2px auto auto;width: 80%;height: 3px;background-color: rgb(127, 127, 127);overflow: hidden;max-width: 80% !important;\"> \n" +
                "              <br> \n" +
                "             </section> \n" +
                "            </section> \n" +
                "           </section> \n" +
                "           <section style=\"margin-top: -14px;border-top: 1px solid rgb(0, 0, 0);height: 1px;overflow: hidden;\"> \n" +
                "            <br> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section data-role=\"paragraph\"> \n" +
                "         <p style=\"line-height: 0em;\"><br></p> \n" +
                "        </section> \n" +
                "        <section style=\"display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 10px;\"> \n" +
                "         <section style=\"width: 11px;height: 11px;border-width: 1px;border-style: solid;border-color: rgb(38, 38, 38);margin-top: 7px;margin-right: 15px;flex-shrink: 0;overflow: hidden;\"> \n" +
                "          <br> \n" +
                "         </section> \n" +
                "         <section data-autoskip=\"1\" style=\"line-height: 1.75em;letter-spacing: 1.5px;font-size: 14px;color: rgb(62, 76, 81);background-image: initial;background-position: initial;background-size: initial;background-repeat: initial;background-attachment: initial;background-origin: initial;background-clip: initial;\"> \n" +
                "          <p itemprop=\"link\" itemscope itemtype=\"https://mp.weixin.qq.com/voc/URL\" style=\"vertical-align:inherit;\"><br></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 10px;\"> \n" +
                "         <section style=\"width: 11px;height: 11px;border-width: 1px;border-style: solid;border-color: rgb(38, 38, 38);margin-top: 7px;margin-right: 15px;flex-shrink: 0;overflow: hidden;\"> \n" +
                "          <br> \n" +
                "         </section> \n" +
                "         <section data-autoskip=\"1\" style=\"line-height: 1.75em;letter-spacing: 1.5px;font-size: 14px;color: rgb(62, 76, 81);background-image: initial;background-position: initial;background-size: initial;background-repeat: initial;background-attachment: initial;background-origin: initial;background-clip: initial;\"> \n" +
                "          <p itemprop=\"link\" itemscope itemtype=\"https://mp.weixin.qq.com/voc/URL\" style=\"vertical-align:inherit;\"><br></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 10px;\"> \n" +
                "         <section style=\"width: 11px;height: 11px;border-width: 1px;border-style: solid;border-color: rgb(38, 38, 38);margin-top: 7px;margin-right: 15px;flex-shrink: 0;overflow: hidden;\"> \n" +
                "          <br> \n" +
                "         </section> \n" +
                "         <section data-autoskip=\"1\" style=\"line-height: 1.75em;letter-spacing: 1.5px;font-size: 14px;color: rgb(62, 76, 81);background-image: initial;background-position: initial;background-size: initial;background-repeat: initial;background-attachment: initial;background-origin: initial;background-clip: initial;\"> \n" +
                "          <p itemprop=\"link\" itemscope itemtype=\"https://mp.weixin.qq.com/voc/URL\" style=\"vertical-align:inherit;\"><br></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 10px;\"> \n" +
                "         <section style=\"width: 11px;height: 11px;border-width: 1px;border-style: solid;border-color: rgb(38, 38, 38);margin-top: 7px;margin-right: 15px;flex-shrink: 0;overflow: hidden;\"> \n" +
                "          <br> \n" +
                "         </section> \n" +
                "         <section data-autoskip=\"1\" style=\"line-height: 1.75em;letter-spacing: 1.5px;font-size: 14px;color: rgb(62, 76, 81);background-image: initial;background-position: initial;background-size: initial;background-repeat: initial;background-attachment: initial;background-origin: initial;background-clip: initial;\"> \n" +
                "          <p itemprop=\"link\" itemscope itemtype=\"https://mp.weixin.qq.com/voc/URL\" style=\"vertical-align:inherit;\"><br></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: flex;justify-content: flex-start;align-items: flex-start;margin-top: 10px;\"> \n" +
                "         <section style=\"width: 11px;height: 11px;border-width: 1px;border-style: solid;border-color: rgb(38, 38, 38);margin-top: 7px;margin-right: 15px;flex-shrink: 0;overflow: hidden;\"> \n" +
                "          <br> \n" +
                "         </section> \n" +
                "         <section data-autoskip=\"1\" style=\"line-height: 1.75em;letter-spacing: 1.5px;font-size: 14px;color: rgb(62, 76, 81);background-image: initial;background-position: initial;background-size: initial;background-repeat: initial;background-attachment: initial;background-origin: initial;background-clip: initial;\"> \n" +
                "          <p itemprop=\"link\" itemscope itemtype=\"https://mp.weixin.qq.com/voc/URL\" style=\"vertical-align:inherit;\"></p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "  </section> \n" +
                "  <section style=\"font-size: 16px;\"> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;margin-top: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;background-color: rgb(95, 156, 239);border-top-left-radius: 10px;border-top-right-radius: 10px;overflow: hidden;padding-top: 15px;padding-bottom: 15px;\"> \n" +
                "     <section style=\"\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"text-align: justify;font-size: 18px;color: rgb(255, 255, 255);letter-spacing: 1px;\"> \n" +
                "       <p style=\"text-align: center;text-wrap: wrap;\"><strong>厦门市人才服务中心新媒体矩阵</strong></p> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "   <section style=\"text-align: left;justify-content: flex-start;display: flex;flex-flow: row;margin-top: -10px;\" powered-by=\"xiumi.us\"> \n" +
                "    <section style=\"display: inline-block;width: 100%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;border-style: solid;border-width: 2px;border-color: rgb(95, 156, 239);\"> \n" +
                "     <section style=\"transform: scale(0.96);transform-origin: center center;margin-top: -4px;margin-bottom: -4px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"justify-content: flex-start;display: flex;flex-flow: row;margin-top: 30px;\"> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.33%;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img data-imgfileid=\"505702287\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_jpg/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRfoEhLhLibyJrjP1pD1LN22PXhtzkQF2BibCspTLVibCmYKZ9Sxr5B0GgA/640?wx_fmt=jpeg&amp;from=appmsg\" data-type=\"jpeg\" data-w=\"344\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;letter-spacing: 1px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">厦门人才网官网</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">公众号</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.3333%;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img class=\"rich_pages wxw-img\" data-imgfileid=\"505702290\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzR0V4ziaEnltEMDZyg9mvRLg0115IqafRsaTia3K4iakQa5YpBbY7b6xCCg/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"344\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;letter-spacing: 1px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">厦门人才中心</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">视频号</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.3333%;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img data-imgfileid=\"505702291\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRibldpUeGrTNnBT2duKoEOfIw8b0o2Q7fjibdlLw4KRuGYhu5mEQTuH2A/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"344\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;letter-spacing: 1px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">厦门人才中心</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">抖音号</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"display: flex;flex-flow: row;text-align: center;justify-content: center;margin-top: 10px;margin-bottom: 5px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"display: inline-block;vertical-align: middle;width: 45%;align-self: center;flex: 0 0 auto;height: auto;\"> \n" +
                "       <section style=\"margin-top: 0.5em;margin-bottom: 0.5em;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"background-color: rgb(95, 156, 239);height: 1px;\"> \n" +
                "         <svg viewbox=\"0 0 1 1\" style=\"float:left;line-height:0;width:0;vertical-align:top;\"></svg> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "      <section style=\"display: inline-block;vertical-align: middle;width: 16px;flex: 0 0 auto;height: auto;border-width: 0px;border-style: none;border-color: rgb(62, 62, 62);align-self: center;\"> \n" +
                "       <section style=\"display: flex;flex-flow: row;justify-content: center;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"display: inline-block;vertical-align: top;width: auto;flex: 100 100 0%;height: auto;align-self: flex-start;\"> \n" +
                "         <section style=\"transform: rotateZ(14deg);\" powered-by=\"xiumi.us\"> \n" +
                "          <section style=\"\"> \n" +
                "           <section style=\"display: inline-block;width: 3px;height: 15px;vertical-align: top;overflow: hidden;background-color: rgb(95, 156, 239);\"> \n" +
                "            <svg viewbox=\"0 0 1 1\" style=\"float:left;line-height:0;width:0;vertical-align:top;\"></svg> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: inline-block;vertical-align: top;width: auto;flex: 100 100 0%;height: auto;align-self: flex-start;\"> \n" +
                "         <section style=\"transform: rotateZ(14deg);\" powered-by=\"xiumi.us\"> \n" +
                "          <section style=\"\"> \n" +
                "           <section style=\"display: inline-block;width: 3px;height: 34px;vertical-align: top;overflow: hidden;background-color: rgb(95, 156, 239);\"> \n" +
                "            <svg viewbox=\"0 0 1 1\" style=\"float:left;line-height:0;width:0;vertical-align:top;\"></svg> \n" +
                "           </section> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "      <section style=\"display: inline-block;vertical-align: middle;width: 45%;align-self: center;flex: 0 0 auto;height: auto;\"> \n" +
                "       <section style=\"margin-top: 0.5em;margin-bottom: 0.5em;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"background-color: rgb(95, 156, 239);height: 1px;\"> \n" +
                "         <svg viewbox=\"0 0 1 1\" style=\"float:left;line-height:0;width:0;vertical-align:top;\"></svg> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"margin-top: 20px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"text-align: justify;font-size: 18px;color: rgb(0, 0, 0);\"> \n" +
                "       <p style=\"text-align: center;text-wrap: wrap;\"><strong>求职直通</strong></p> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"text-align: center;justify-content: center;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"display: inline-block;width: 96%;vertical-align: top;align-self: flex-start;flex: 0 0 auto;height: auto;\"> \n" +
                "       <section style=\"margin: 10px 0%;text-align: right;justify-content: flex-end;display: flex;flex-flow: row;\" powered-by=\"xiumi.us\"> \n" +
                "        <section style=\"display: inline-block;vertical-align: middle;width: 10%;padding-right: 5px;padding-left: 5px;align-self: center;flex: 0 0 auto;height: auto;\"> \n" +
                "         <section style=\"text-align: center;margin-right: 0%;margin-left: 0%;transform: rotateX(180deg);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "          <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "           <img data-imgfileid=\"505702288\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_gif/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRgXyHjNCGOVKfBzniaTUKDEYic9SZWGnfBCPza3OgicphDIicp5KiaUD2tWA/640?wx_fmt=gif&amp;from=appmsg\" data-type=\"gif\" data-w=\"400\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: inline-block;vertical-align: bottom;width: 80%;flex: 0 0 auto;height: auto;align-self: flex-end;\"> \n" +
                "         <section style=\"margin-top: 5px;margin-right: 0%;margin-left: 0%;transform: translate3d(1px, 0px, 0px);\" powered-by=\"xiumi.us\"> \n" +
                "          <section style=\"text-align: center;letter-spacing: 0px;color: rgb(0, 0, 0);\"> \n" +
                "           <p>扫码关注，了解更多优质岗位详情</p> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"display: inline-block;vertical-align: middle;width: 10%;flex: 0 0 auto;height: auto;padding-right: 5px;padding-left: 5px;align-self: center;\"> \n" +
                "         <section style=\"text-align: center;margin-right: 0%;margin-left: 0%;transform: rotateX(180deg);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "          <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "           <img data-imgfileid=\"505702289\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_gif/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRgXyHjNCGOVKfBzniaTUKDEYic9SZWGnfBCPza3OgicphDIicp5KiaUD2tWA/640?wx_fmt=gif&amp;from=appmsg\" data-type=\"gif\" data-w=\"400\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "          </section> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "     <section style=\"transform: scale(0.96);transform-origin: center center;margin-top: -4px;margin-bottom: -4px;\" powered-by=\"xiumi.us\"> \n" +
                "      <section style=\"justify-content: flex-start;display: flex;flex-flow: row;margin-top: 15px;\"> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.33%;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img class=\"rich_pages wxw-img\" data-imgfileid=\"505702293\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRia4Lkqz9oGfbhyRwiaYWa6ia8Q14fwx2WJFoR9DbBwclfdBvAP0WpfUGg/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"344\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">厦门人才网官网</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">www.xmrc.com.cn</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.3333%;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img class=\"rich_pages wxw-img\" data-imgfileid=\"505702292\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRKFiaVkp7LWbpSKrEVHAKFZrSy07jWacadCxmdaZJblpS4jL3vgPjrPA/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"344\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 10px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">百城千校万人</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">云端大招聘</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "       <section style=\"display: inline-block;vertical-align: top;width: 33.3333%;align-self: flex-start;flex: 0 0 auto;\"> \n" +
                "        <section style=\"text-align: center;transform: translate3d(-1px, 0px, 0px);line-height: 0;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"vertical-align: middle;display: inline-block;line-height: 0;width: 80%;height: auto;\"> \n" +
                "          <img data-imgfileid=\"505702294\" data-ratio=\"1\" data-s=\"300,640\" src=\"https://mmbiz.qpic.cn/mmbiz_png/eibRxe5MlnCtI4GlLUTWJaY8VFynxxLzRkhnZdILrpNf5nvYBmVwY3QZ10o0rtFTmfJRrt7h9TOrbh2MpASRib0g/640?wx_fmt=png&amp;from=appmsg\" data-type=\"png\" data-w=\"400\" style=\"vertical-align: middle;width: 100%;\"> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "        <section style=\"text-align: center;margin-bottom: 20px;\" powered-by=\"xiumi.us\"> \n" +
                "         <section style=\"text-align: justify;font-size: 12px;color: rgb(0, 0, 0);\"> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">人才登鹭·C位无限</p> \n" +
                "          <p style=\"text-align: center;text-wrap: wrap;\">直播引才</p> \n" +
                "         </section> \n" +
                "        </section> \n" +
                "       </section> \n" +
                "      </section> \n" +
                "     </section> \n" +
                "    </section> \n" +
                "   </section> \n" +
                "  </section> \n" +
                "  <p style=\"text-align: center;\"><img class=\"rich_pages wxw-img\" data-backh=\"88\" data-backw=\"578\" data-galleryid=\"\" data-imgfileid=\"505676609\" data-ratio=\"0.153\" src=\"https://mmbiz.qpic.cn/mmbiz_gif/eibRxe5MlnCtlys2iawB9PXXNe2bicTiaH9NjP7btJLQkxH6ibqOibSPWH8StLDXZgadT0HicKic1Hbic9zvOIL7EnAFonA/640?wx_fmt=gif\" data-type=\"gif\" data-w=\"1000\" style=\"width: 100%;height: auto;\"></p> \n" +
                "  <p style=\"display: none;\"> \n" +
                "   <mp-style-type data-value=\"3\"></mp-style-type></p> \n" +
                " </body>\n" +
                "</html>";
        // 使用Jsoup解析HTML  
        Document document = Jsoup.parse(html);
        // 获取所有链接元素  
        Elements linkElements = document.select("img");
        List<String> links = new ArrayList<>();
        for (Element linkElement : linkElements) {
            String src = linkElement.attr("src");
            links.add(src.substring(1, src.length() - 2));
            // System.out.println("链接: " + src.substring(2, src.length() - 2));
        }
        Elements spanElements = document.select("span");
        for (Element spanElement : spanElements) {
            String aClass = spanElement.attr("class");
            if ("errorLink".equals(aClass)) {
                links.add(spanElement.ownText());
                // System.out.println("链接: " + spanElement.ownText());
            }
        }

        // 获取所有链接元素  
        Elements linkElements2 = document.select("a");
        // 遍历链接元素，并获取每个链接对应的<head>标签  
        for (Element linkElement : linkElements2) {
            String href = linkElement.attr("abs:href");
            if (StringUtils.hasText(href)) {
                links.add(href);
                // System.out.println("链接: " + href);
            }
        }

        // 过滤需要的文章和标题
        for (String link : links) {
            if (link.contains("mmbiz.qpic.cn")) {
                System.out.println("Ignore Link=" + link);
                continue;
            }
            Document document1 = Jsoup.connect(link)
                    .ignoreHttpErrors(true)
                    .timeout(2000)
                    // 方便抓取https内容
                    .validateTLSCertificates(false)
                    .get();
            String title = document1.title();
            System.out.println("Fetch Link=" + link + "、" + "Title=" + title);
        }
    }
}
