package com.sxhxjy.towermonitor.base;

import android.os.Environment;

/**
 * Project constants go here ...
 *
 * @author Michael Zhao
 */
public interface MyConstants {
    boolean IS_DEBUG = true;

    String STORAGE_IMAGE = Environment.getExternalStorageDirectory().getPath() + "/myclient/images/";
}