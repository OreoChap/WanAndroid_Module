package com.oreooo.baselibrary.pojo;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/4/29
 */
public class ArticleDatas {
    private Integer audit;
    private String apkLink;
    private String author;
    private String chapterId;
    private String chapterName;
    private boolean collect;
    private String courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private String id;
    private String link;
    private String niceDate;
    private String niceShareDate;
    private String origin;
    private String prefix;
    private String projectLink;
    private String publishTime;
    private String superChapterId;
    private String superChapterName;
    private Integer realSuperChapterId;
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getRealSuperChapterId() {
        return realSuperChapterId;
    }

    public void setRealSuperChapterId(Integer realSuperChapterId) {
        this.realSuperChapterId = realSuperChapterId;
    }

    // Tag
    private List<ArticleDatasTags> tags;

    // 文章标题
    private String title;
    private String type;
    private String userId;
    private String visible;
    private String zan;
    private Long shareDate;
    private String shareUser;
    private int selfVisible;
    private boolean canEdit;
    private String descMd;

    public String getDescMd() {
        return descMd;
    }

    public void setDescMd(String descMd) {
        this.descMd = descMd;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getSelfVisible() {
        return selfVisible;
    }

    public void setSelfVisible(int selfVisible) {
        this.selfVisible = selfVisible;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public Long getShareDate() {
        return shareDate;
    }

    public void setShareDate(Long shareDate) {
        this.shareDate = shareDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(String superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public List<ArticleDatasTags> getTags() {
        return tags;
    }

    public void setTags(List<ArticleDatasTags> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }
}
