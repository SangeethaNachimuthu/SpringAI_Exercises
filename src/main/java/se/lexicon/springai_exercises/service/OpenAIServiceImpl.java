package se.lexicon.springai_exercises.service;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiChatModel chatModel;

    @Autowired
    public OpenAIServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String processSimpleChatQuery(String question) {

        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }

        return chatModel.call(question);

    }
}
