package com.raf.rdd.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;

import lombok.NoArgsConstructor;

/**
 * The persistent class for the ITEM_TYPE database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "ITEM_TYPE", schema = "RDD")
@NoArgsConstructor
public class ItemType extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 4637860278006269029L;

}
