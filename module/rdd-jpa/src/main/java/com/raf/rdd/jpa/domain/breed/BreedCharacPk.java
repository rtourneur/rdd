package com.raf.rdd.jpa.domain.breed;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable pripary key for the table BREED_CHARAC.
 * 
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BreedCharacPk implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = 2885966545939488368L;

  /** The breed name. */
  @Column(name = "BREED", nullable = false, length = 30)
  private String breedName;

  /** The characteristic. */
  @Column(name = "CHARACTERISTIC", nullable = false, length = 30)
  private String characName;

  /**
   * Generate the toString.
   *
   * @see Object#toString()
   */
  @Override
  public String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    builder.append("breed", this.breedName).append("characName", this.characName);
    return builder.toString();
  }

}
