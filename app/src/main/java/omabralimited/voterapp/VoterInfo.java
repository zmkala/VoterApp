package omabralimited.voterapp;

import android.widget.DatePicker;

import java.io.Serializable;

/**
 * Created by omachi on 8/26/18.
 */
public class VoterInfo implements Serializable {
  private String voter_name;
  private String profilePic;
  private String id;
  private String idNum;
  private String timeOfReg;
  private String areaReg;
  private String dob;
  private String phoneNumber;
  private String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVoter_name() {
    return voter_name;
  }

  public void setVoter_name(String voter_name) {
    this.voter_name = voter_name;
  }
  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }
  public String getTimeOfReg() {
    return timeOfReg;
  }

  public void setTimeOfReg(String timeOfReg) {
    this.timeOfReg = timeOfReg;
  }
  public String getAreaReg() {
    return areaReg;
  }

  public void setAreaReg(String areaReg) {
    this.areaReg = areaReg;
  }
  public String getdob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }



  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }
}
