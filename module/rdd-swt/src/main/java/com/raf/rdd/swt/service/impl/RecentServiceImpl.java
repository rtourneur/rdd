package com.raf.rdd.swt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.swt.service.RecentService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for {@link RecentService}.
 * 
 * @author RAF
 */
@Service
@NoArgsConstructor
@Slf4j
public class RecentServiceImpl implements RecentService {

  /**
   * Return the list of recent figures names.
   * 
   * @return the list of recent figures names
   * @throws ServiceException
   *           when exception occurs while loading ini file
   * @see RecentService#getRecentFigures()
   */
  @Override
  public List<String> getRecentFigures() throws ServiceException {
    return loadIni();
  }

  /**
   * Add a recent figure name to the list.
   * 
   * @param name
   *          the name of the figure
   * @throws ServiceException
   *           when exception occurs while loading ini file
   * @see RecentService#addRecentFigure(String)
   */
  @Override
  public void addRecentFigure(final String name) throws ServiceException {
    String toRemove = null;
    final List<String> recents = loadIni();
    for (final String recent : recents) {
      if (name.equals(recent)) {
        toRemove = name;
      }
    }
    if (toRemove != null) {
      recents.remove(toRemove);
    }
    if (recents.size() == MAX_RECENT) {
      recents.remove(MAX_RECENT - 1);
    }
    recents.add(0, name);
    saveIni(recents);
  }

  private List<String> loadIni() throws ServiceException {
    final List<String> recents = new ArrayList<>();
    final File iniFile = getIniFile();
    if (iniFile.exists() && iniFile.canRead()) {
      try {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(iniFile));
        String recent;
        for (int index = 1; index <= 4; index++) {
          recent = properties.getProperty("recent." + index);
          if (recent != null) {
            recents.add(recent);
          }
        }
      } catch (IOException e) {
        log.warn("Exception while loading ini file " + iniFile.getAbsolutePath() + " : " + e.getMessage(), e);
        throw new ServiceException(e, "error.inifile", iniFile.getAbsolutePath());
      }
    }
    return recents;
  }

  private static void saveIni(final List<String> recents) throws ServiceException {
    String recent;
    final Properties properties = new Properties();
    for (int index = 0; index < recents.size(); index++) {
      recent = recents.get(index);
      properties.setProperty("recent." + (index + 1), recent);
    }
    final File iniFile = getIniFile();
    try {
      if (!iniFile.exists()) {
        iniFile.createNewFile();
      }
      properties.store(new FileWriter(iniFile), "rdd.ini");
    } catch (IOException e) {
      log.warn("Exception while saving ini file " + iniFile.getAbsolutePath() + " : " + e.getMessage(), e);
      throw new ServiceException(e, "error.inifile", iniFile.getAbsolutePath());
    }

  }

  private static File getIniFile() {
    final String userDir = System.getProperty("user.dir");
    return new File(userDir, "rdd.ini");
  }

}
