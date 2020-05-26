package com.synectiks.library.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Library.
 */
@Entity
@Table(name = "library")
public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "no_of_copies")
    private Long noOfCopies;

    @Column(name = "book_no")
    private Long bookNo;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "unique_no")
    private Long uniqueNo;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "subject")
    private Long subject;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Library bookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public Library author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public Library noOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
        return this;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public Library bookNo(Long bookNo) {
        this.bookNo = bookNo;
        return this;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Library additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public Library uniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
        return this;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public Long getBatchId() {
        return batchId;
    }

    public Library batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSubject() {
        return subject;
    }

    public Library subject(Long subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Library)) {
            return false;
        }
        return id != null && id.equals(((Library) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Library{" +
            "id=" + getId() +
            ", bookTitle='" + getBookTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", noOfCopies=" + getNoOfCopies() +
            ", bookNo=" + getBookNo() +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            ", uniqueNo=" + getUniqueNo() +
            ", batchId=" + getBatchId() +
            ", subject=" + getSubject() +
            "}";
    }
}
