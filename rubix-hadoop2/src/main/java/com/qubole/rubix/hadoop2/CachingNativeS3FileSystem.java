/**
 * Copyright (c) 2016. Qubole Inc
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. See accompanying LICENSE file.
 */
package com.qubole.rubix.hadoop2;

import com.qubole.rubix.core.CachingFileSystem;
import com.qubole.rubix.core.ClusterManagerInitilizationException;
import com.qubole.rubix.spi.ClusterType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.s3native.NativeS3FileSystem;

import java.io.IOException;
import java.net.URI;

/**
 * Created by sakshia on 28/7/16.
 */
public class CachingNativeS3FileSystem extends CachingFileSystem<NativeS3FileSystem>
{
  private static final Log LOG = LogFactory.getLog(CachingNativeS3FileSystem.class);
  private static final String SCHEME = "s3n";

  public CachingNativeS3FileSystem()
      throws IOException
  {
    super();
  }

  public String getScheme()
  {
    return SCHEME;
  }

  @Override
  public void initialize(URI uri, Configuration conf)
      throws IOException
  {
    try {
      initializeClusterManager(conf, ClusterType.HADOOP2_CLUSTER_MANAGER);
      super.initialize(uri, conf);
    }
    catch (ClusterManagerInitilizationException ex) {
      throw new IOException(ex);
    }
  }
}
