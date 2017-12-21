package com.raf.rdd.jpa.domain.breed;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;
import com.raf.rdd.jpa.domain.skill.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the CHARACTER database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "BREED", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Breed extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 8507566198354619047L;

  /** The natural defense. */
  @Column(name = "DEFENSE", nullable = false, precision = 1)
  private int defense;
  
  /** The characteristics. */
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "BREED_CHARAC", schema = "RDD", joinColumns = { @JoinColumn(name = "BREED") })
  private Set<BreedCharac> charBreeds;

  /** The forbidden skills. */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "BREED_SKILL", schema = "RDD", joinColumns = {
      @JoinColumn(name = "BREED", referencedColumnName = "NAME") }, inverseJoinColumns = {
          @JoinColumn(name = "SKILL", referencedColumnName = "NAME") })
  private Set<Skill> forbiddens;

  /**
   * Append the object values for the to string builder.
   *
   * @param builder
   *          the to string builder
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("defense", this.defense).append("charBreeds", this.charBreeds).append("forbiddens", this.forbiddens);
  }

}
