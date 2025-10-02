package com.asalkar.healthyhub.entity.cms;

import jakarta.persistence.*;

@Entity
@Table(name = "cms_section")
public class CmsSection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", nullable = false)
    private CmsPage page;
    
    @Column(name = "type", length = 40, nullable = false)
    private String type;
    
    @Column(name = "heading", length = 200)
    private String heading;
    
    @Column(name = "body_html", columnDefinition = "TEXT")
    private String bodyHtml;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }
    
    public CmsPage getPage() { return page; }
    public void setPage(CmsPage page) { this.page = page; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getHeading() { return heading; }
    public void setHeading(String heading) { this.heading = heading; }
    
    public String getBodyHtml() { return bodyHtml; }
    public void setBodyHtml(String bodyHtml) { this.bodyHtml = bodyHtml; }
    
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}