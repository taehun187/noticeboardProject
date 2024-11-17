package com.taehun.noticeboard.post.tag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long tagId;
    @Column(
        nullable = false,
        length = 15
    )
    private String tagData;

    public Tag(String tagData) {
        this.tagData = tagData;
    }

    public static Tag createFreeTag(String tag) {
        return new Tag(tag);
    }

    public static TagBuilder builder() {
        return new TagBuilder();
    }

    public long getTagId() {
        return this.tagId;
    }

    public String getTagData() {
        return this.tagData;
    }

    public Tag() {
    }

    public Tag(final long tagId, final String tagData) {
        this.tagId = tagId;
        this.tagData = tagData;
    }

    public static class TagBuilder {
        private long tagId;
        private String tagData;

        TagBuilder() {
        }

        public TagBuilder tagId(final long tagId) {
            this.tagId = tagId;
            return this;
        }

        public TagBuilder tagData(final String tagData) {
            this.tagData = tagData;
            return this;
        }

        public Tag build() {
            return new Tag(this.tagId, this.tagData);
        }

        public String toString() {
            return "Tag.TagBuilder(tagId=" + this.tagId + ", tagData=" + this.tagData + ")";
        }
    }
}