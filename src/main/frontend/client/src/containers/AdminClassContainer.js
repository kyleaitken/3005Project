import { useCallback, useEffect, useState } from "react";
import { getAllClasses, getRooms, addClass, updateClass, deleteClass } from "../api/adminApi";
import { getAvailableTrainers } from "../api/memberApi";
import AdminClassesTable from "../components/AdminClassesTable";
import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import { Navigate } from 'react-router-dom';
import AddClassForm from "../components/AddClassForm";

const AdminClassContainer = () => {
    const [classes, setClasses] = useState([]);
    const [trainers, setTrainers] = useState([]);
    const [rooms, setRooms] = useState([]);
    const {userId, userType} = useAuth();

    const getClasses = useCallback(async () => {
        if (userType === "Admin") {
            try {
                const res = await getAllClasses();
                setClasses(res);
            } catch {
                window.alert("Unable to fetch classes");
            }
        }
    }, [userType]);

    const getTrainers = useCallback(async() => {
        if (userType === "Admin") {
            try {
                const res = await getAvailableTrainers();
                setTrainers(res);
            } catch {
                window.alert("Unable to fetch trainers");
            }
        }
    }, [userType]);

    const fetchRooms = useCallback(async() => {
        if (userType === "Admin") {
            try {
                const res = await getRooms();
                setRooms(res);
            } catch {
                window.alert("Unable to fetch rooms");
            }
        }
    }, [userType]);

    useEffect(() => {
        getClasses();
        getTrainers();
        fetchRooms();
    }, [getClasses, getTrainers, fetchRooms])

    if (!userId || userType !== "Admin") {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    const tryUpdateClass = async (classId, className, classDate, classTime, roomId, trainerId) => {
        console.log('update: ', classId, className, classDate, classTime, roomId, trainerId);
        const classUpdateRequest = {
            className,
            date: classDate,
            time: classTime,
            roomId,
            trainerId
        };
        try {
            const res = await updateClass(classId, classUpdateRequest);
            console.log(res);
            if (res.message === "Success") {
                window.alert("Class Updated")
                getClasses();
            } else {
                window.alert("Unable to update class")
            }
        } catch (error) {
            window.alert("Error: Unable to update class ", error)
        }
    };

    const handleDeleteClass = async (classId) => {
        console.log('delete: ', classId);
        try {
            const res = await deleteClass(classId);
            if (res === "Success") {
                getClasses();
            } else {
                window.alert("Unable to delete class")
            }

        } catch {
            window.alert("Unable to delete class")

        }
    };

    const handleAddClass = async (className, classDate, classTime, classRoom, classTrainer) => {
        console.log(className, classDate, classTime, classTrainer, classRoom);
        try {
            const res = await addClass(className, classDate, classTime, classTrainer, classRoom);
            if (res.message === "Success") {
                getClasses();
            } else {
                window.alert("Unable to add class.")
            }
        } catch {
            window.alert("Unable to add class.")
        }

    }

    return (
        <AdminClassesView>
            <AddClassForm handleAddClass={handleAddClass} trainers={trainers} rooms={rooms}/>
            <AdminClassesTable 
                classes={classes} 
                trainers={trainers} 
                rooms={rooms} 
                tryUpdateClass={tryUpdateClass} 
                handleDeleteClass={handleDeleteClass}
            />
        </AdminClassesView>
    )
}

export default AdminClassContainer;

const AdminClassesView = styled.div`
    display: flex;
    flex-direction: column;
`

