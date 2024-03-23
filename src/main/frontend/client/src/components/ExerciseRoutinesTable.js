import React from 'react';
import styled from 'styled-components';

const ExerciseRoutines = ({ handleDeleteRoutine, routines }) => {
  return (
    <div>
      {routines && routines.length > 0 ? (
        routines.map((routine, index) => (
          <RoutineSection key={routine.routineId}>
            <RoutineHeader>
              <SubTitle>Routine {index + 1}</SubTitle>
              <RemoveRoutineButton onClick={() => handleDeleteRoutine(routine.routineId)} >Remove Routine</RemoveRoutineButton>
            </RoutineHeader>
            <Table>
              <thead>
                <tr>
                  <TableHeader>Exercise</TableHeader>
                  <TableHeader>Sets</TableHeader>
                  <TableHeader>Reps</TableHeader>
                  <TableHeader>Weight</TableHeader>
                  <TableHeader>Duration (mins)</TableHeader>
                </tr>
              </thead>
              <tbody>
                {routine.exercises.map(exercise => (
                  <tr key={exercise.logId}>
                    <TableCell>{exercise.exerciseName}</TableCell>
                    <TableCell>{exercise.numSets || '-'}</TableCell>
                    <TableCell>{exercise.numReps || '-'}</TableCell>
                    <TableCell>{exercise.weight ? `${exercise.weight} kg` : '-'}</TableCell>
                    <TableCell>{exercise.duration || '-'}</TableCell>
                  </tr>
                ))}
              </tbody>
            </Table>
          </RoutineSection>
        ))
      ) : (
        <NoRoutinesMessage>No Exercise Routines</NoRoutinesMessage>
      )}
    </div>
  );
};

export default ExerciseRoutines;


const RoutineSection = styled.section`
  margin-bottom: 40px;
`;

const RoutineHeader = styled.div`
  display: flex;
  margin-left: 50px;
  align-items: center;
`;

const SubTitle = styled.h2`
  margin: 30px 0;
`;

const RemoveRoutineButton = styled.button`
  padding: 5px 10px;
  margin-left: 50px;

  background-color: #E63333;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 20px;

  &:hover {
      background-color: #AB0606;
  }
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
`;

const TableHeader = styled.th`
  background-color: #f4f4f4;
  padding: 10px;
  border: 1px solid #ddd;
`;

const TableCell = styled.td`
  text-align: center;
  padding: 10px;
  border: 1px solid #ddd;
`;


const NoRoutinesMessage = styled.p`
  text-align: center;
`;
