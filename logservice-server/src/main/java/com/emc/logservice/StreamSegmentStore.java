package com.emc.logservice;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Defines all operations that are supported on a StreamSegment.
 */
public interface StreamSegmentStore
{
    /**
     * Appends a range of bytes at the end of a StreamSegment. The byte range will be appended as a contiguous block,
     * however there is no guarantee of ordering between different calls to this method.
     *
     * @param streamSegmentName The name of the StreamSegment to add to.
     * @param data              The data to add.
     * @param timeout           Timeout for the operation
     * @return A CompletableFuture that, when completed normally, will contain the offset within the StreamSegment where
     * the add was added. If the operation failed, it will contain the exception that caused the failure.
     * @throws NullPointerException     If any of the arguments are null.
     * @throws IllegalArgumentException If the StreamSegment Name is invalid (NOTE: this doesn't check if the StreamSegment
     *                                  does not exist - that exception will be set in the returned CompletableFuture).
     */
    CompletableFuture<Long> append(String streamSegmentName, byte[] data, Duration timeout);

    /**
     * Initiates a Read operation on a particular StreamSegment and returns a ReadResult which can be used to consume the
     * read data.
     *
     * @param streamSegmentName The name of the StreamSegment to read from.
     * @param offset            The offset within the stream to start reading at.
     * @param maxLength         The maximum number of bytes to read.
     * @param timeout           Timeout for the operation.
     * @return A ReadResult instance that can be used to consume the read data.
     * @throws NullPointerException      If any of the arguments are null.
     * @throws IllegalArgumentException  If any of the arguments are invalid.
     */
    ReadResult read(String streamSegmentName, long offset, int maxLength, Duration timeout);

    /**
     * Gets information about a StreamSegment.
     *
     * @param streamSegmentName The name of the StreamSegment.
     * @param timeout           Timeout for the operation.
     * @return A CompletableFuture that, when completed normally, will contain the result. If the operation failed, it
     * will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid.
     */
    CompletableFuture<StreamSegmentInformation> getStreamInfo(String streamSegmentName, Duration timeout);

    /**
     * Creates a new StreamSegment.
     *
     * @param streamSegmentName The name of the StreamSegment to create.
     * @param timeout           Timeout for the operation.
     * @return A CompletableFuture that, when completed normally, will indicate the operation completed. If the operation
     * failed, it will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid.
     */
    CompletableFuture<Void> createStreamSegment(String streamSegmentName, Duration timeout);

    /**
     * Creates a new Batch and maps it to a Parent StreamSegment.
     *
     * @param parentStreamSegmentName The name of the Parent StreamSegment to create a batch for.
     * @param timeout                 Timeout for the operation.
     * @return A CompletableFuture that, when completed normally, will contain the name of the newly created batch.
     * If the operation failed, it will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid.
     */
    CompletableFuture<String> createBatch(String parentStreamSegmentName, Duration timeout);

    /**
     * Merges a Batch into its parent StreamSegment.
     *
     * @param batchName The name of the Batch StreamSegment to merge.
     * @param timeout   Timeout for the operation.
     * @return A CompletableFuture that, when completed normally, will contain the offset within the parent StreamSegment
     * where the batch has been merged at. If the operation failed, it will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid.
     */
    CompletableFuture<Long> mergeBatch(String batchName, Duration timeout);

    /**
     * Seals a StreamSegment for modifications
     *
     * @param streamSegmentName The name of the StreamSegment to seal.
     * @param timeout           Timeout for the operation
     * @return A CompletableFuture that, when completed normally, will contain the final length of the StreamSegment.
     * If the operation failed, it will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid.
     */
    CompletableFuture<Long> sealStreamSegment(String streamSegmentName, Duration timeout);

    /**
     * Deletes a StreamSegment.
     *
     * @param streamSegmentName The name of the StreamSegment to delete.
     * @param timeout           Timeout for the operation.
     * @return A CompletableFuture that, when completed normally, will indicate the operation completed. If the operation
     * failed, it will contain the exception that caused the failure.
     * @throws IllegalArgumentException If any of the arguments are invalid
     */
    CompletableFuture<Void> deleteStreamSegment(String streamSegmentName, Duration timeout);
}
