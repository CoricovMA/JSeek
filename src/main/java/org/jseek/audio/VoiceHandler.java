package org.jseek.audio;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;

public class VoiceHandler implements AudioReceiveHandler, AudioSendHandler {
    @Override
    public boolean canProvide() {
        return false;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return null;
    }

    @Override
    public boolean isOpus() {
        return false;
    }

    @Override
    public boolean canReceiveCombined() {
        return false;
    }

    @Override
    public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {

    }
}
