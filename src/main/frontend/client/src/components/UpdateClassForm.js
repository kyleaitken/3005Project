import styled from "styled-components";
import React, { useState } from 'react';

const UpdateClassForm = (props) => {
    const { classData, trainers, rooms, handleUpdateClass } = props;

    const [className, setClassName] = useState(classData.className);
    const [classDate, setClassDate] = useState(classData.date);
    const [classTime, setClassTime] = useState(classData.time);
    const [classRoom, setClassRoom] = useState(classData.roomName);
    const [classTrainer, setClassTrainer] = useState(classData.trainerName);

    const handleSubmit = (event) => {
        event.preventDefault();
        const trainerId = trainers.find(trainer => trainer.trainerName === classTrainer)?.trainerId;
        const roomId = rooms.find(room => room.roomName === classRoom)?.roomId;
        handleUpdateClass(classData.classId, className, classDate, classTime, roomId, trainerId)
    };

    return (
        <FormView>
        <FormInputView>
            <Form onSubmit={handleSubmit}>
            <Label>
                Class:
                <Input 
                    type="string" 
                    placeholder="Class Name" 
                    value={className} 
                    onChange={(e) => setClassName(e.target.value)} 
                />
            </Label>
            <Label>
                Date:
                <Input 
                    type="date" 
                    value={classDate} 
                    onChange={(e) => setClassDate(e.target.value)}
                />
            </Label>
            <Label>
                Time:
                <Input
                    type="number" 
                    placeholder="Class Time" 
                    value={classTime} 
                    onChange={(e) => setClassTime(e.target.value)}
                />
            </Label>
            <Label>Trainer:
                <Select 
                    value={classTrainer} 
                    onChange={(e) => setClassTrainer(e.target.value)}>
                    <option value="">Select a trainer</option>
                    {trainers?.map(trainer => (
                        <option key={trainer.trainerId} value={trainer.trainerName}>
                            {trainer.trainerName}
                        </option>
                    ))}
                </Select>
            </Label>
            <Label>Room:
                <Select 
                    value={classRoom} 
                    onChange={(e) => setClassRoom(e.target.value)}>
                    <option value="">Select a Room</option>
                    {rooms?.map(room => (
                        <option key={room.roomId} value={room.roomName}>
                            {room.roomName}
                        </option>
                    ))}
                </Select>
            </Label>
            <UpdateClassButton type="submit">Update Class</UpdateClassButton>
            </Form>
        </FormInputView>
        </FormView>
    )
}

export default UpdateClassForm;

const FormView = styled.div`
  margin: 20px 0px 30px 40px;
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
  font-size: 18px;
`;

const Select = styled.select`
    margin-top: 5px;
    font-size: 16px;
`;

const Input = styled.input`
  margin-top: 5px;
  width: 400px;
  font-size: 18px;
`;

const UpdateClassButton = styled.button`
  padding: 5px 5px;
  width: 150px;
  align-self: center;

  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
      background-color: #0056b3;
  }
`;

const FormInputView = styled.div``