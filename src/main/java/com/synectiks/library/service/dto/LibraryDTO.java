package com.synectiks.library.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.library.domain.Library} entity.
 */
public class LibraryDTO implements Serializable {

    private Long id;

    private String bookTitle;

    private String author;

    private Long noOfCopies;

    private Long bookNo;

    private String additionalInfo;

    private Long uniqueNo;

    private Long batchId;

    private Long subject;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibraryDTO libraryDTO = (LibraryDTO) o;
        if (libraryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), libraryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LibraryDTO{" +
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
