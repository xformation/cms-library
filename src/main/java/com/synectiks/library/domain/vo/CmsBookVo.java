package com.synectiks.library.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.library.domain.Batch;
import com.synectiks.library.domain.Department;
import com.synectiks.library.domain.Library;
import com.synectiks.library.domain.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsBookVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private  Integer noOfCopiesAvailable;
    private String bookStatus;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate receivedDate;
    private String strIssueDate;
    private String strDueDate;
    private String strReceivedDate;
    private Long batchId;
    private Long departmentId;
    private Long libraryId;
    private Long studentId;
    private Long branchId;
    private Library library;
    private Department department;
    private Batch batch;
    private Student student;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private List<CmsBookVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStrIssueDate() {
        return strIssueDate;
    }

    public void setStrIssueDate(String strIssueDate) {
        this.strIssueDate = strIssueDate;
    }

    public String getStrDueDate() {
        return strDueDate;
    }

    public void setStrDueDate(String strDueDate) {
        this.strDueDate = strDueDate;
    }

    public String getStrReceivedDate() {
        return strReceivedDate;
    }

    public void setStrReceivedDate(String strReceivedDate) {
        this.strReceivedDate = strReceivedDate;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<CmsBookVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsBookVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsBookVo{" +
            "id=" + id +
            ", noOfCopiesAvailable=" + noOfCopiesAvailable +
            ", bookStatus='" + bookStatus + '\'' +
            ", issueDate=" + issueDate +
            ", dueDate=" + dueDate +
            ", receivedDate=" + receivedDate +
            ", strIssueDate='" + strIssueDate + '\'' +
            ", strDueDate='" + strDueDate + '\'' +
            ", strReceivedDate='" + strReceivedDate + '\'' +
            ", batchId=" + batchId +
            ", departmentId=" + departmentId +
            ", libraryId=" + libraryId +
            ", studentId=" + studentId +
            ", branchId=" + branchId +
            ", library=" + library +
            ", department=" + department +
            ", batch=" + batch +
            ", student=" + student +
            ", dataList=" + dataList +
            '}';
    }
}
