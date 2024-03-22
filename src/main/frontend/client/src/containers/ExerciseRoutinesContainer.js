import { getExerciseRoutines, addExerciseRoutine, deleteExerciseRoutine } from "../api/memberApi";
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

    return (
        <RoutinesView>
            <Title>Exercise Routines</Title>
            <AddRoutineButton onClick={addNewRoutine}>Add Routine</AddRoutineButton>
            <ExerciseRoutinesTable handleDeleteRoutine={handleDeleteRoutine} routines={routines}/>
            <AddExerciseForm />
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
  margin: 20px auto;
  padding: 10px 20px;
`;