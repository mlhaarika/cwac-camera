/***
  Copyright (c) 2014 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.commonsware.cwac.camera;

import android.hardware.Camera;

public class PictureTransaction implements Camera.ShutterCallback {
  CameraHost host=null;
  boolean needBitmap=false;
  boolean needByteArray=true;
  private Object tag=null;
  boolean mirrorFFC=false;
  boolean useSingleShotMode=false;
  int displayOrientation=0;
  String flashMode=null;
  int jpegQuality=100;
  CameraView cameraView=null;

  public PictureTransaction(CameraHost host) {
    this.host=host;
  }

  public PictureTransaction needBitmap(boolean needBitmap) {
    this.needBitmap=needBitmap;

    return(this);
  }

  public PictureTransaction needByteArray(boolean needByteArray) {
    this.needByteArray=needByteArray;

    return(this);
  }

  public Object getTag() {
    return(tag);
  }

  public PictureTransaction tag(Object tag) {
    this.tag=tag;

    return(this);
  }

  boolean useSingleShotMode() {
    return(useSingleShotMode || host.useSingleShotMode());
  }

  boolean mirrorFFC() {
    return(mirrorFFC || host.mirrorFFC());
  }

  public PictureTransaction useSingleShotMode(boolean useSingleShotMode) {
    this.useSingleShotMode=useSingleShotMode;

    return(this);
  }

  public PictureTransaction mirrorFFC(boolean mirrorFFC) {
    this.mirrorFFC=mirrorFFC;

    return(this);
  }

  public PictureTransaction flashMode(String flashMode) {
    this.flashMode=flashMode;

    return(this);
  }

  /**
   * If the picture needs to be fixed up, then by default it is saved at 100% JPEG quality.  This
   * optional parameter can be used to override that quality.  When this is set to a value other
   * than 100, the image will *always* be compressed and saved at that quality (even if it would not
   * have previously needed any manipulation).
   *
   * This option currently only applies when {@link #needByteArray(boolean)} is set.  It does not
   * apply when using {@link #needBitmap(boolean)}.
   *
   * Valid values are 1-100.
   */
  public PictureTransaction jpegQuality(int jpegQuality) {
    this.jpegQuality=jpegQuality;

    return(this);
  }

  PictureTransaction displayOrientation(int displayOrientation) {
    this.displayOrientation=displayOrientation;

    return(this);
  }

  @Override
  public void onShutter() {
    Camera.ShutterCallback cb=host.getShutterCallback();

    if (cb != null) {
      cb.onShutter();
    }
  }
}
