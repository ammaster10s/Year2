import openai

# Set up your OpenAI API key
api_key = "your_api_key_here"
openai.api_key = api_key~

# Function to interact with GPT model
def ask_chatgpt(question):
    response = openai.Completion.create(
        engine="text-davinci-003",
        prompt=question,
        max_tokens=50
    )
    return response.choices[0].text.strip()

# Main function for user interaction
def main():
    print("Welcome to ChatGPT! Type 'exit' to quit.")
    while True:
        user_input = input("You: ")
        if user_input.lower() == "exit":
            print("ChatGPT: Goodbye!")
            break
        response = ask_chatgpt(user_input)
        print("ChatGPT:", response)

if __name__ == "__main__":
    main()
