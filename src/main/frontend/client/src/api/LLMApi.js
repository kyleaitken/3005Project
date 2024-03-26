const OpenAI = require("openai");


export const sendExerciseRecommendationRequest = async (exercises) => {
    const openai = new OpenAI({apiKey: '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', dangerouslyAllowBrowser: true}); 

    const prompt = exercises.map((routine, index) => {
        const exercisesList = routine.exercises.map(exercise =>
            `${exercise.exerciseName}: ${exercise.numSets} sets x ${exercise.numReps} reps, weight: ${exercise.weight}lbs`
        ).join(", ");
        return `Routine ${index + 1}: ${exercisesList}`;
    }).join(". ");

    const promptText = `Here are this person's exercise routines: ${prompt}. Can you please make a recommendation for only 1 exercise in the form of something like "You should try adding: Deadlift: 4 sets 5 reps, to Routine 1" to add to one of this member's exercise routines? Come up with your own exercise that isn't in the routine and don't include any additional text.`;

    try {
        const completion = await openai.chat.completions.create({
            model: "gpt-3.5-turbo",
            messages: [
                { role: "system", content: "You are a helpful assistant." },
                { role: "user", content: promptText},
            ],
        });

        // const generatedContent = completion.data.choices[0].message.content;
        // console.log(completion);
        return completion.choices[0].message.content;

    } catch (error) {
        console.error("Failed to send exercise recommendation request:", error);
    }
};

 
  

