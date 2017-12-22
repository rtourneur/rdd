package com.raf.rdd.jpa.domain.character;

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
public class CharValuePk implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = 2885966545939488368L;

  /** The character id. */
  @Column(name = "FIGURE_ID", nullable = false)
  private Integer figureId;

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
    builder.append("figure", this.figureId).append("characName", this.characName);
    return builder.toString();
  }

}
