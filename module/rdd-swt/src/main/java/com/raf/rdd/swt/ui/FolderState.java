package com.raf.rdd.swt.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for folder states.
 * 
 * @author RAF
 */
@AllArgsConstructor
@Getter
public enum FolderState {
  /** The read state. */
  READ(false),
  /** The create state. */
  CREATE(true),
  /** The update state. */
  UPDATE(true);

  /** Indicate if the state is an editable state. */
  private final boolean editable;
}
