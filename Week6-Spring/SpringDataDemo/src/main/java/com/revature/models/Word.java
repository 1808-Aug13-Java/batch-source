package com.revature.models;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="WORD")
public class Word {
  public Word() {
  }   

  @Id
  @Column
  @NotNull
  private String headword;
  @Column
  private String significance;
  @Column
  private String kind;
  
  public Word(String headword, String significance, String kind) {
    this.headword = headword;
    this.significance=significance;
    this.kind=kind;
  }

  /**
   * Get headword.
   *
   * @return headword as String.
   */
  public String getHeadword()
  {
      return headword;
  }
  
  /**
   * Set headword.
   *
   * @param headword the value to set.
   */
  public void setHeadword(String headword)
  {
      this.headword = headword;
  }
  
  /**
   * Get significance.
   *
   * @return significance as String.
   */
  public String getSignificance()
  {
      return significance;
  }
  
  /**
   * Set significance.
   *
   * @param significance the value to set.
   */
  public void setSignificance(String significance)
  {
      this.significance = significance;
  }
  
  /**
   * Get kind.
   *
   * @return kind as String.
   */
  public String getKind()
  {
      return kind;
  }
  
  /**
   * Set kind.
   *
   * @param kind the value to set.
   */
  public void setKind(String kind)
  {
      this.kind = kind;
  }
  
}
