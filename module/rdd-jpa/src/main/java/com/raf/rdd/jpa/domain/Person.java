package com.raf.rdd.jpa.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.rdd.jpa.enums.GenderConverter;
import com.raf.rdd.jpa.enums.GenderEnum;
import com.raf.rdd.jpa.enums.HandConverter;
import com.raf.rdd.jpa.enums.HandEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The embeddable fields describe a person.
 *
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = 2625990522310463618L;

  /** The Time of Birth. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BIRTH_TIME", nullable = false)
  private DraconicHour birthTime;

  /** The gender. */
  @Column(name = "GENDER", nullable = false, length = 1)
  @Convert(converter = GenderConverter.class)
  private GenderEnum gender;

  /** The age, between 18 and 40 years. */
  @Column(name = "AGE", nullable = false, precision = 2)
  private int age;

  /** The weight, in kg. */
  @Column(name = "WEIGHT", nullable = false, precision = 3)
  private int weight;

  /** The size, in cm. */
  @Column(name = "SIZE", nullable = false, precision = 3)
  private int size;

  /** The hairs description. */
  @Column(name = "HAIRS", nullable = false, length = 50)
  private String hairs;

  /** The eyes color. */
  @Column(name = "EYES", nullable = false, length = 50)
  private String eyes;

  /** The beauty value. */
  @Column(name = "BEAUTY", nullable = false, precision = 2)
  private int beauty;

  /** The guiding hand. */
  @Column(name = "HAND", nullable = false, length = 1)
  @Convert(converter = HandConverter.class)
  private HandEnum hand;

  /**
   * Return the string representation for this object.
   *
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    if (this.birthTime != null && DraconicHour.class.equals(this.birthTime.getClass())) {
      builder.append("birthTime", this.birthTime);
    }
    builder.append("gender", this.gender).append("age", this.age).append("weight", this.weight)
        .append("size", this.size).append("hairs", this.hairs).append("eyes", this.eyes).append("beauty", this.beauty)
        .append("hand", this.hand);
    return builder.toString();
  }

}
