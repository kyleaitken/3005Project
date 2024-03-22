import styled from "styled-components";

const AddExerciseForm = () => {
    return (
        <FormView>
        <h3>Add Exercise To Routine</h3>
        <FormInputView>
            <Form>
            <Label>
                Routine Number:
                <Input type="number" placeholder="e.g., 1" />
            </Label>
            <Label>
                Exercise:
                <Input type="text" placeholder="e.g., Bench Press" />
            </Label>
            <Label>
                Sets:
                <Input type="number" placeholder="e.g., 3" />
            </Label>
            <Label>
                Reps:
                <Input type="number" placeholder="e.g., 10" />
            </Label>
            <Label>
                Weight (kg):
                <Input type="number" placeholder="e.g., 80" />
            </Label>
            <Label>
                Duration (mins):
                <Input type="number" placeholder="e.g., 0" />
            </Label>
            <AddExerciseButton>Add Exercise</AddExerciseButton>
            </Form>
        </FormInputView>
        </FormView>
    )
}

export default AddExerciseForm;

const FormView = styled.div`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-self: center;
`;

const FormTitle = styled.h3`
  margin-bottom: 10px;
`;

const Form = styled.form`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 50px;
  align-items: center;
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
`;

const Input = styled.input`
  margin-top: 5px;
  width: 400px;
`;

const AddExerciseButton = styled.button`
  padding: 10px 20px;
  width: 200px;
  align-self: center;
`;

const FormInputView = styled.div``