import { getExerciseRoutines, 
        addExerciseRoutine, 
        deleteExerciseRoutine, 
        addExerciseToRoutine,
        removeExerciseFromRoutine } from "../api/memberApi";
import { useCallback, useEffect, useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { Navigate } from 'react-router-dom';
import styled from 'styled-components';
import ExerciseRoutinesTable from "../components/ExerciseRoutinesTable";
import AddExerciseForm from "../components/AddExerciseForm";


const ExerciseRoutinesContainer = () => {
    const [routines, setRoutines] = useState(null);
    const {userId} = useAuth();

    const getRoutines = useCallback(async () => {
        if (userId) {
            const routinesResponse = await getExerciseRoutines(userId);
            console.log(routinesResponse);
            setRoutines(routinesResponse);
        }
    }, [userId]);


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
            console.log(addRoutineResponse);
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
                console.log('deleted');
                getRoutines(userId);
            }
        } catch {
            window.alert("Unable to delete exercise routine");
        }
    }

    const handleAddExerciseToRoutine = async (routineNum, exerciseName, sets, reps, duration, weight) => {
        console.log(routineNum, exerciseName, sets, reps, duration, weight);
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
                console.log('exercise removed');
                getRoutines(userId);
            } else {
                window.alert("Unable to remove exercise")
            }
        } catch {
            window.alert("Unable to remove exercise")
        }
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
            <AddExerciseForm handleAddExerciseToRoutine={handleAddExerciseToRoutine}/>
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