package com.raf.rdd.jpa.domain.breed;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractEntity;
import com.raf.fwk.jpa.domain.DomainEntity;
import com.raf.rdd.jpa.domain.Characteristic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the BREED_CHARAC database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "BREED_CHARAC", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "identifier", callSuper = false)
public class BreedCharac extends AbstractEntity implements DomainEntity<BreedCharacPk>, Comparable<BreedCharac> {

  /** Serial UID. */
  private static final long serialVersionUID = 6984559951156074320L;

  /** The identifier. */
  @EmbeddedId
  private BreedCharacPk identifier;

  /** The breed. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "BREED", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_BREED_CHARAC_BREED"))
  private Breed breed;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CHARACTERISTIC", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_BREED_CHARAC_CHARACTERISTIC"))
  private Characteristic characteristic;

  /** The modifier value. */
  @Column(name = "MODIFIER", nullable = false, precision = 1)
  private int modifier;

  /** The limit value. */
  @Column(name = "LIMIT", precision = 2)
  private Integer limit;

  /**
   * Compare breed characteristics using the rank in the characteristic.
   *
   * @param breedCharac
   *          the breed characteristic to compare
   * @see Comparable#compareTo(Object)
   */
  @Override
  public final int compareTo(final BreedCharac breedCharac) {
    return this.characteristic.compareTo(breedCharac.characteristic);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * @see AbstractEntity#append(ToStringBuilder)
   */
  @Override
  protected void append(final ToStringBuilder builder) {
    builder.append("identifier", this.identifier);
    if (this.characteristic != null && Characteristic.class.equals(this.characteristic.getClass())) {
      builder.append("characteristic", this.characteristic);
    }
    builder.append("modifier", this.modifier).append("limit", this.limit);

  }

}
