/**
 * Copyright (c) 2017 Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.client.stream;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * The configuration of a Stream.
 */
@Data
@Builder
public class StreamConfiguration implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * API to return scaling policy.
     *
     */
    private final ScalingPolicy scalingPolicy;

    /**
     * API to return retention policy.
     *
     */
    private final RetentionPolicy retentionPolicy;
    
    /**
     * The duration after the last call to {@link EventStreamWriter#noteTime(long)} which the
     * timestamp should be considered valid before it is forgotten. Meaning that after this long of
     * not calling {@link EventStreamWriter#noteTime(long)} readers that call
     * {@link EventStreamReader#getCurrentTimeWindow()} will receive a `null` when they are at the
     * corresponding position in the stream.
     */
    private final long timestampAggrigationTimeout;

    public static final class StreamConfigurationBuilder {
        private ScalingPolicy scalingPolicy = ScalingPolicy.fixed(1);
        
        /**
         * Scope is specified on stream creation.
         * @param scope ignored
         * @deprecated Does nothing. 
         */
        @Deprecated
        public StreamConfigurationBuilder scope(String scope) {
            return this;
        }
        
        /**
         * Stream name is specified on stream creation.
         * @param streamName ignored
         * @deprecated Does nothing. 
         */
        @Deprecated
        public StreamConfigurationBuilder streamName(String streamName) {
            return this;
        }
    }
}
