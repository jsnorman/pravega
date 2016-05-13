package com.emc.logservice.ReadIndex;

import com.emc.logservice.ReadResultEntry;
import com.emc.logservice.ReadResultEntryContents;

import java.util.concurrent.CompletableFuture;

/**
 * Read Result Entry with no content that marks the end of the StreamSegment.
 * The getContent() method will throw an IllegalStateException if invoked.
 */
public class EndOfStreamSegmentReadResultEntry extends ReadResultEntry
{
    /**
     * Constructor.
     *
     * @param streamSegmentOffset The offset in the StreamSegment that this entry starts at.
     * @param requestedReadLength The maximum number of bytes requested for read.
     */
    public EndOfStreamSegmentReadResultEntry(long streamSegmentOffset, int requestedReadLength)
    {
        super(streamSegmentOffset, requestedReadLength);
    }

    @Override
    public boolean isEndOfStreamSegment()
    {
        return true;
    }

    @Override
    public CompletableFuture<ReadResultEntryContents> getContent()
    {
        throw new IllegalStateException("EndOfStream ReadResult Entry does not have any content.");
    }
}
