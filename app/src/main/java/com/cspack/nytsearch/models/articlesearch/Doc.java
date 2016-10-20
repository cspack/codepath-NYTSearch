
package com.cspack.nytsearch.models.articlesearch;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Doc {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("abstract")
    @Expose
    private Object _abstract;
    @SerializedName("print_page")
    @Expose
    private Object printPage;
    @SerializedName("blog")
    @Expose
    private List<String> blog = new ArrayList<String>();
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia = new ArrayList<Multimedia>();
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = new ArrayList<Object>();
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("byline")
    @Expose
    private Byline byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private Object wordCount;
    @SerializedName("slideshow_credits")
    @Expose
    private Object slideshowCredits;

    /**
     * 
     * @return
     *     The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Doc withWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Doc withSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    /**
     * 
     * @return
     *     The leadParagraph
     */
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     * 
     * @param leadParagraph
     *     The lead_paragraph
     */
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public Doc withLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
        return this;
    }

    /**
     * 
     * @return
     *     The _abstract
     */
    public Object getAbstract() {
        return _abstract;
    }

    /**
     * 
     * @param _abstract
     *     The abstract
     */
    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public Doc withAbstract(Object _abstract) {
        this._abstract = _abstract;
        return this;
    }

    /**
     * 
     * @return
     *     The printPage
     */
    public Object getPrintPage() {
        return printPage;
    }

    /**
     * 
     * @param printPage
     *     The print_page
     */
    public void setPrintPage(Object printPage) {
        this.printPage = printPage;
    }

    public Doc withPrintPage(Object printPage) {
        this.printPage = printPage;
        return this;
    }

    /**
     * 
     * @return
     *     The blog
     */
    public List<String> getBlog() {
        return blog;
    }

    /**
     * 
     * @param blog
     *     The blog
     */
    public void setBlog(List<String> blog) {
        this.blog = blog;
    }

    public Doc withBlog(List<String> blog) {
        this.blog = blog;
        return this;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    public Doc withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * 
     * @return
     *     The multimedia
     */
    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    /**
     * 
     * @param multimedia
     *     The multimedia
     */
    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public Doc withMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
        return this;
    }

    /**
     * 
     * @return
     *     The headline
     */
    public Headline getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public Doc withHeadline(Headline headline) {
        this.headline = headline;
        return this;
    }

    /**
     * 
     * @return
     *     The keywords
     */
    public List<Object> getKeywords() {
        return keywords;
    }

    /**
     * 
     * @param keywords
     *     The keywords
     */
    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public Doc withKeywords(List<Object> keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * 
     * @return
     *     The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Doc withPubDate(String pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    /**
     * 
     * @return
     *     The documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * 
     * @param documentType
     *     The document_type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Doc withDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * 
     * @return
     *     The newsDesk
     */
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     * 
     * @param newsDesk
     *     The news_desk
     */
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public Doc withNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
        return this;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The section_name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Doc withSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    /**
     * 
     * @return
     *     The subsectionName
     */
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     * 
     * @param subsectionName
     *     The subsection_name
     */
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public Doc withSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
        return this;
    }

    /**
     * 
     * @return
     *     The byline
     */
    public Byline getByline() {
        return byline;
    }

    /**
     * 
     * @param byline
     *     The byline
     */
    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public Doc withByline(Byline byline) {
        this.byline = byline;
        return this;
    }

    /**
     * 
     * @return
     *     The typeOfMaterial
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     * 
     * @param typeOfMaterial
     *     The type_of_material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public Doc withTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Doc withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The wordCount
     */
    public Object getWordCount() {
        return wordCount;
    }

    /**
     * 
     * @param wordCount
     *     The word_count
     */
    public void setWordCount(Object wordCount) {
        this.wordCount = wordCount;
    }

    public Doc withWordCount(Object wordCount) {
        this.wordCount = wordCount;
        return this;
    }

    /**
     * 
     * @return
     *     The slideshowCredits
     */
    public Object getSlideshowCredits() {
        return slideshowCredits;
    }

    /**
     * 
     * @param slideshowCredits
     *     The slideshow_credits
     */
    public void setSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }

    public Doc withSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
        return this;
    }

}
