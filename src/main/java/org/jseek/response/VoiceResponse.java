package org.jseek.response;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nullable;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

public class VoiceResponse extends Response {

    private AudioManager audioManager;
    private VoiceChannel voiceChannel;
    private String audioResFile;

    public VoiceResponse(MessageReceivedEvent event) {
        super(event);
        setVoiceChannel();
        openConnection();
    }

    @Override
    public void send() {
        try {
            File file = new File("C:\\Users\\AMC\\IdeaProjects\\JSeek\\src\\main\\resources\\shutup.mp3");
            Mp3File mp3File = new Mp3File(file);
            audioManager.closeAudioConnection();

        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    private void setVoiceChannel() {
        try {
            this.voiceChannel = this.getEvent().getMember().getVoiceState().getChannel();
        } catch (NullPointerException npe) {
            this.getEvent().getChannel().sendMessage(
                    String.format("You're not connected to a voice channel, %s. Do so, and try again",
                            this.getEvent().getAuthor().getName())).queue();
        }
    }

    private void openConnection() {
        this.audioManager = this.getEvent().getGuild().getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
    }

    public void setAudioResFile(String audioResFile) {
        this.audioResFile = audioResFile;
    }
}
