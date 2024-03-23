import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

const ScheduleSessionForm = (props) => {
    const [sessionDate, setSessionDate] = useState('');
    const [sessionTime, setSessionTime] = useState('');
    const [selectedTrainer, setSelectedTrainer] = useState('');
    const [trainerTimes, setTrainerTimes] = useState([]);

    const { handleAddSession, trainers = [] } = props;

    useEffect(() => {
        if (selectedTrainer) {
            const trainer = trainers.find(trainer => trainer.trainerId.toString() === selectedTrainer);
            const timeOptions = [];
            for (let i = trainer.startTime; i <= trainer.endTime; i++) {
                timeOptions.push(i);
            }
            setTrainerTimes(timeOptions);
            setSessionTime(trainer.startTime)
            return;
        }
        setTrainerTimes(Array.from({ length: 14 }, (_, i) => 7 + i));
    }, [selectedTrainer, trainers]);

    const handleSubmit = (event) => {
        event.preventDefault();
        handleAddSession(sessionDate, sessionTime, selectedTrainer)
        console.log(sessionDate, sessionTime, selectedTrainer);
    };

    return (
        <>
        <Header><h1>Add New Session</h1></Header>
        <FormContainer onSubmit={handleSubmit}>
            <Label>Date:
                <Input 
                    type="date" 
                    value={sessionDate} 
                    onChange={(e) => setSessionDate(e.target.value)} 
                />
            </Label>
            <Label>Time:
                <Select 
                    value={sessionTime} 
                    onChange={(e) => setSessionTime(e.target.value)} 
                >
                     {trainerTimes.map(time => (
                        <option key={time} value={time}>
                            {time}
                        </option>
                    ))}
                </Select>
            </Label>
            <Label>Trainer:
                <Select 
                    value={selectedTrainer} 
                    onChange={(e) => setSelectedTrainer(e.target.value)}>
                    <option value="">Select a trainer</option>
                    {trainers?.map(trainer => (
                        <option key={trainer.trainerId} value={trainer.trainerId}>
                            {trainer.trainerName}
                        </option>
                    ))}
                </Select>
            </Label>
            <SubmitButton type="submit">Add Session</SubmitButton>
        </FormContainer>
        </>
    );
};

export default ScheduleSessionForm;

// Styled components
const FormContainer = styled.form`
    display: flex;
    align-items: center;
    gap: 5px;
    margin-bottom: 20px;
`;

const Label = styled.label`
    display: flex;
    margin: 0px 50px 0px 50px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    font-size: 16px;
`;

const Input = styled.input`
    margin-top: 5px;
    font-size: 16px;
    
`;

const Select = styled.select`
    margin-top: 5px;
    font-size: 16px;
`;

const SubmitButton = styled.button`
    padding: 10px 20px;
    margin-left: 40px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;

    &:hover {
        background-color: #0056b3;
    }
`;

const Header = styled.div`
    margin-left: 50px;
`


