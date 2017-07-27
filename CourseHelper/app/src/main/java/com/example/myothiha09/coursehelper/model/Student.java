package com.example.myothiha09.coursehelper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/17/2017.
 */

public class Student implements Serializable{
  private String name;
  private String university;
  private List<CommitmentRequest> commitmentRequestList;
  public Student() {
    this.name  = "";
    this.university = "";
    commitmentRequestList = new ArrayList<>();
  }
  public Student(String name, String university) {
    this.name = name;
    this.university = university;
    commitmentRequestList = new ArrayList<>();
  }

  public void editCommitmentRequest(int position, CommitmentRequest request) {
    commitmentRequestList.remove(position);
    commitmentRequestList.add(position, request);
  }

  public void addCommitmentRequest(CommitmentRequest request) {
    if (!commitmentRequestList.contains(request)) commitmentRequestList.add(request);
  }

  public CommitmentRequest[] getCommmitmentRequestAsArray() {
    return commitmentRequestList.toArray(new CommitmentRequest[commitmentRequestList.size()]);
  }

  public List<CommitmentRequest> getCommitmentRequests() {
    return commitmentRequestList;
  }

  public boolean haveThisCommitment(Commitment commitment) {
    for (CommitmentRequest request : commitmentRequestList) {
      if (request.getCommitment().getName().equals(commitment.getName())) return true;
    }
    return false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }
}
