import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import NavigationSideBar from "../components/NavigationSideBar";
import LoginScreen from "../screens/LoginScreen";
import MemberSchedule from "./MemberSchedule";
import MemberSessions from "./MemberSessions";
import MemberClasses from "./MemberClasses";
import AvailableClasses from "./AvailableClasses";
import ExerciseRoutinesContainer from "./ExerciseRoutinesContainer";
import MemberGoals from "./MemberGoals";
import MemberInvoices from "./MemberInvoices";
import MemberHealthInfo from "./MemberHealthInfo";
import MemberProfile from "./MemberProfile";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';



const AppContent = () => {
    const { isLoggedIn } = useAuth();

    return (
        <AppContentView>
            {isLoggedIn && <NavigationSideBar />}
            <Routes>
                <Route path="/" element={<LoginScreen />} />
                <Route path="/schedule" element={<MemberSchedule />} />
                <Route path="/sessions" element={<MemberSessions />} />
                <Route path="/classes" element={<MemberClasses />} />
                <Route path="/joinClass" element={<AvailableClasses />} />
                <Route path="/routines" element={<ExerciseRoutinesContainer />} />
                <Route path="/goals" element={<MemberGoals />} />
                <Route path="/memberInvoices" element={<MemberInvoices />} />
                <Route path="/healthInfo" element={<MemberHealthInfo/>} />
                <Route path="/profile" element={<MemberProfile />} />
            </Routes>
        </AppContentView>
    )

}


export default AppContent;

const AppContentView = styled.div`
    display: flex;
`