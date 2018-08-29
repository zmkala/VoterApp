package omabralimited.voterapp;

import java.io.Serializable;

public class DeadInfo implements Serializable {
  private String dead_name;
  private String profilePic;
  private String id;
  private String idNum;
  private String timeOfReg;
  private String areaReg;
  private String dob;
  private String deathTime;
  private String deathCause;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDead_name() {
    return dead_name;
  }

  public void setDead_name(String dead_name) {
    this.dead_name = dead_name;
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
  public String getDeathCause() {
    return deathCause;
  }

  public void setDeathCause(String deathCause) {
    this.deathCause = deathCause;
  }
  public String getDeathTime() {
    return deathTime;
  }

  public void setDeathTime(String deathTime) {
    this.deathTime = deathTime;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }
}
