import styled from "styled-components";
import React, { useState, useEffect } from 'react';

const AddExerciseForm = (props) => {
    const [routineNum, setRoutineNum] = useState('');
    const [exerciseName, setExerciseName] = useState('');
    const [numSets, setNumSets] = useState('');
    const [numReps, setNumReps] = useState('');
    const [duration, setDuration] = useState('');
    const [weight, setWeight] = useState('');

    const { handleAddExerciseToRoutine } = props;

    const handleSubmit = (event) => {
        event.preventDefault();
        if (routineNum > 0) {
            handleAddExerciseToRoutine(routineNum, exerciseName, numSets, numReps, duration, weight)
        } else {
            window.alert("Please enter a valid routine number");
        }
    };

    return (
        <FormView>
        <h2>Add Exercise To Routine</h2>
        <FormInputView>
            <Form onSubmit={handleSubmit}>
            <Label>
                Routine Number:
                <Input 
                    type="number" 
                    placeholder="e.g., 1" 
                    value={routineNum} 
                    onChange={(e) => setRoutineNum(e.target.value)} 
                />
            </Label>
            <Label>
                Exercise:
                <Input 
                    type="text" 
                    placeholder="e.g., Bench Press"
                    value={exerciseName} 
                    onChange={(e) => setExerciseName(e.target.value)}
                />
            </Label>
            <Label>
                Sets:
                <Input
                    type="number" 
                    placeholder="e.g., 3" 
                    value={numSets} 
                    onChange={(e) => setNumSets(e.target.value)}
                />
            </Label>
            <Label>
                Reps:
                <Input 
                    type="number" 
                    placeholder="e.g., 10" 
                    value={numReps} 
                    onChange={(e) => setNumReps(e.target.value)}
                />
            </Label>
            <Label>
                Weight (kg):
                <Input 
                    type="number" 
                    placeholder="e.g., 80" 
                    value={weight}
                    onChange={(e) => setWeight(e.target.value)}
                />
            </Label>
            <Label>
                Duration (mins):
                <Input 
                    type="number" 
                    placeholder="e.g., 0" 
                    value={duration}
                    onChange={(e) => setDuration(e.target.value)}
                />
            </Label>
            <AddExerciseButton type="submit">Add Exercise</AddExerciseButton>
            </Form>
        </FormInputView>
        </FormView>
    )
}

export default AddExerciseForm;

const FormView = styled.div`
  margin: 20px 0px 50px 40px;
  display: flex;
  flex-direction: column;
  width: 50%;
`;


const Form = styled.form`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  align-items: center;
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  font-size: 20px;
`;

const Input = styled.input`
  margin-top: 5px;
  width: 400px;
  font-size: 20px;
`;

const AddExerciseButton = styled.button`
  padding: 10px 20px;
  width: 200px;
  align-self: center;

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

const FormInputView = styled.div``