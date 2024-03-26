import { getExerciseRoutines, 
        addExerciseRoutine, 
        deleteExerciseRoutine, 
        addExerciseToRoutine,
        removeExerciseFromRoutine } from "../api/memberApi";
import {sendExerciseRecommendationRequest} from "../api/LLMApi";
import { useCallback, useEffect, useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { Navigate } from 'react-router-dom';
import styled from 'styled-components';
import ExerciseRoutinesTable from "../components/ExerciseRoutinesTable";
import AddExerciseForm from "../components/AddExerciseForm";


const ExerciseRoutinesContainer = () => {
    const [routines, setRoutines] = useState(null);
    const [exerciseSuggestion, setExerciseSuggestion] = useState('');
    const {userId} = useAuth();

    const fetchExerciseSuggestion = useCallback(async (fetchedRoutines) => {
        if (!fetchedRoutines) return; 
        const exercisesResponse = await sendExerciseRecommendationRequest(fetchedRoutines);
        setExerciseSuggestion(exercisesResponse);
    }, [])

    const getRoutines = useCallback(async () => {
        if (userId) {
            const routinesResponse = await getExerciseRoutines(userId);
            console.log(routinesResponse);
            setRoutines(routinesResponse);
            fetchExerciseSuggestion(routinesResponse);
        }
    }, [userId, fetchExerciseSuggestion]);


    useEffect(() => {
        getRoutines();
    }, [getRoutines]);

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    const addNewRoutine = async () => {
        try {
            const addRoutineResponse = await addExerciseRoutine(userId);
            if (addRoutineResponse.message === "Success") {
                getRoutines(userId);
            }
        } catch {
            window.alert("Unable to add exercise routine");
        }
    }

    const handleDeleteRoutine = async (routineId) => {
        try {
            const response = await deleteExerciseRoutine(userId, routineId);
            if (response === "Success") {
                getRoutines(userId);
            }
        } catch {
            window.alert("Unable to delete exercise routine");
        }
    }

    const handleAddExerciseToRoutine = async (routineNum, exerciseName, sets, reps, duration, weight) => {
        if (routines && routines.length >= routineNum) {
            const routineId = routines[routineNum - 1].routineId; 

            try {
                const response = await addExerciseToRoutine(userId, routineId, exerciseName, sets, reps, duration, weight);
                if (response.message === "Success") {
                    getRoutines(userId);
                } else {
                    window.alert("Unable to add exercise to routine");
                }
            } catch {
                window.alert("Unable to add exercise to routine");
            }

        } else {
            window.alert("Please enter a valid routine number");
        }
    }

    const handleRemoveExerciseFromRoutine = async (logId) => {
        try {
            const res = await removeExerciseFromRoutine(userId, logId);
            if (res === "Success") {
                getRoutines(userId);
            } else {
                window.alert("Unable to remove exercise")
            }
        } catch {
            window.alert("Unable to remove exercise")
        }
    }

    const ExerciseRecommendation = ({suggestion}) => {
        return (
            <SuggestionView>
            {suggestion && <h3>Suggestion</h3>}
            <p>{suggestion}</p>
            </SuggestionView>
        )
    }


    return (
        <RoutinesView>
            <Title>Exercise Routines</Title>
            <AddRoutineButton onClick={addNewRoutine}>Add Routine</AddRoutineButton>
            <ExerciseRoutinesTable 
                handleDeleteRoutine={handleDeleteRoutine} 
                handleRemoveExerciseFromRoutine={handleRemoveExerciseFromRoutine} 
                routines={routines}
            />
            <AddExerciseSection>
                <AddExerciseForm handleAddExerciseToRoutine={handleAddExerciseToRoutine}/>
                <RecommendationView>
                {routines && routines.length > 0 && <ExerciseRecommendation suggestion={exerciseSuggestion} />}
                </RecommendationView>
            </AddExerciseSection>
        </RoutinesView>
    )
}

export default ExerciseRoutinesContainer;

const RoutinesView = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
`
const Title = styled.h1`
  text-align: center;
  margin-top: 20px;
`;

const AddRoutineButton = styled.button`
    align-self: center;
  width: 200px;
  padding: 10px 20px;

  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 20px;

  &:hover {
      background-color: #0056b3;
  }
`;

const AddExerciseSection = styled.div`
  display: flex;
`

const RecommendationView = styled.div`
  margin-left: 200px;
  width: 400px;
  align-self: center;
`

const SuggestionView = styled.div`
  display: flex;
  flex-direction: column;
`