import { getCompletedGoals, getInProgressGoals, setGoalComplete, deleteGoal, addGoal } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";
import React, { useEffect, useState, useCallback } from 'react';
import styled from 'styled-components';
import { Navigate } from 'react-router-dom';
import GoalsTable from "../components/GoalsTable";

const AddGoalForm = ({ onAddGoal }) => {

    const [targetDate, setTargetDate] = useState("");
    const [description, setDescription] = useState("");
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        // Assuming onAddGoal expects an object with description and targetDate
        await onAddGoal(description, targetDate);
        // Reset form fields after submitting
        setDescription("");
        setTargetDate("");
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Label>
                Description:
                <Input 
                    type="text" 
                    value={description} 
                    onChange={(e) => setDescription(e.target.value)} 
                />
            </Label>
            <Label>
                Target Date:
                <Input 
                    type="date" 
                    value={targetDate} 
                    onChange={(e) => setTargetDate(e.target.value)} 
                />
            </Label>
            <AddGoalButton type="submit">Add Goal</AddGoalButton>
        </Form>
    );
};

const MemberGoals = () => {
    const [completedGoals, setCompletedGoals] = useState([]);
    const [inProgressGoals, setInProgressGoals] = useState([])


    const {userId} = useAuth();

    const getGoals = useCallback(async () => {
        if (userId) {
            try {
                const completedGoalsResponse = await getCompletedGoals(userId);
                if (completedGoalsResponse) {
                    setCompletedGoals(completedGoalsResponse);
                } 
            } catch {
                console.error("Unable to get completed goals")
            }

            try {
                const inProgressGoalsResponse = await getInProgressGoals(userId);
                if (inProgressGoalsResponse) {
                    setInProgressGoals(inProgressGoalsResponse);
                } 
            } catch {
                console.error("Unable to get completed goals")
            }
        }
    }, [userId]);

    useEffect(() => {
        getGoals();
    }, [getGoals, userId])

    const handleCompleteGoal = async (goalId) => {
        try {
            const res = await setGoalComplete(userId, goalId);
            if (res === "Success") {
                getGoals();
            } else {
                window.alert("Unable to complete goal")
            }
        } catch {
            window.alert("Unable to complete goal")
        }
    };

    const handleAddGoal = async (description, targetDate) => {
        console.log('des: ', description)
        console.log('date: ', targetDate)

        try {
            const res = await addGoal(userId, description, targetDate);
            if (res.message === "Success") {
                getGoals();
                console.log("goal added");
            } else {
                window.alert("Unable to add goal");
            }
        } catch {
            window.alert("Unable to add goal");
        }
    };

    const handleDeleteGoal = async (goalId) => {
        try {
            const res = await deleteGoal(userId, goalId);
            if (res === "Success") {
                getGoals();
            } else {
                window.alert("Unable to delete goal")
            }
        } catch {
            window.alert("Unable to delete goal")
        }
    };

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <GoalsView>
            <AddGoalForm onAddGoal={handleAddGoal}/>
            <GoalsTable 
                handleCompleteGoal={handleCompleteGoal} 
                handleDeleteGoal={handleDeleteGoal}
                handleAddGoal={handleAddGoal}
                goals={inProgressGoals}
                type={"In Progress"}
            />
            <GoalsTable 
                goals={completedGoals}
                type={"Completed"}
            />
        </GoalsView>
    )
}

export default MemberGoals;

const GoalsView = styled.div`
    display: flex;
    flex-direction: column;
    flex-grow: 1;
`

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 50px 0px 0px 50px;
`;

const Label = styled.label`
  display: flex;
  margin: 10px 0px;
  flex-direction: column;
  font-size: 16px;
`;

const Input = styled.input`
  margin-top: 5px;
  width: 600px;
  font-size: 20px;
`;

const AddGoalButton = styled.button`
    padding: 10px 20px;
    width: 150px;
    margin-top: 10px;

    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;

    &:hover {
        background-color: #0056b3;
    }
`