package se.lexicon.springai_exercises.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.springai_exercises.dto.ApplicationParameters;
import se.lexicon.springai_exercises.dto.MeetingNotes;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiChatModel chatModel;

    private final ChatClient chatClient;

    @Autowired
    public OpenAIServiceImpl(OpenAiChatModel chatModel, ChatClient.Builder builder ) {
        this.chatModel = chatModel;
        this.chatClient = builder.build();
    }

    @Override
    public String processSimpleChatQuery(String question) {

        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }

        return chatModel.call(question);

    }

    @Override
    public String generateApplicationStatus(ApplicationParameters params) {

        if (params == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        SystemMessage systemMessage = SystemMessage.builder()
                .text("""
                        You are a professional application reviewer.
                        
                        Your Role:
                         - Your job is to write clear, polite, and accurate replies
                         - Always be empathetic, concise, and helpful

                        Guidelines:
                         - Under 120 words clear decision
                         - Professional tone
                         - Use bullet points or sections
                        """)
                .build();

        String userInput = String.format("""
                        Write a response to the candidate about the status of the application:
                        
                        Name: %s
                        Background: %s
                        Experience: %s
                        Motivation: %s
                        Skills: %s
                        
                        Include:
                         - Professional email and a short reasoning(Feedback)
                        
                        Instructions:
                         - Review each application.
                         - Decide to Accept or Reject the application.
                        
                        """,
                params.name(),
                params.background(),
                params.experience(),
                params.motivation(),
                params.skills());

        UserMessage userMessage = UserMessage.builder()
                .text(userInput)
                .build();

        Prompt prompt = Prompt.builder()
                .messages(systemMessage, userMessage)
                .chatOptions(
                        ChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.2)
                                .maxTokens(1400)
                                .build()
                )
                .build();

        ChatResponse response = chatModel.call(prompt);

        String content = response.getResult() != null
                ? response.getResult().getOutput().getText()
                : null;

        return (content != null && !content.isEmpty())
                ? content
                : "Sorry, I couldn't generate a response at this moment";
    }

    @Override
    public String generateMeetingNotes(MeetingNotes input) {

        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        ChatResponse response = chatClient.prompt()
                .system("""
                        You are one of the team members.
                        
                        Your role:
                        - To write clear action items discussed
                        - Always be clear, concise, and structured
                        
                        Guidelines:
                        - Clear and short action items
                        - Professional tone and Structures the output
                        - Identify the action items and Summarize the meeting
                        - Use bullet points or sections
                        """)
                .user(String.format("""
                        Create a clear action items from the meeting:
                        
                        Input: %s
                        
                        Include:
                        1. what was discussed
                        2. what decisions were made
                        3. what the next steps are
                        """,
                        input))
                .options(ChatOptions.builder()
                        .model("gpt-4o")
                        .maxTokens(1400)
                        .temperature(0.2))
                .call()
                .chatResponse();

        String content = (response != null && response.getResult() != null)
                ? response.getResult().getOutput().getText()
                : null;
        return (content != null && !content.isBlank())
                ? content
                : "Sorry, I couldn't generate the action items at the moment.";
    }
}
