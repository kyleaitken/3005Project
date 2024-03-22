import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import NavigationSideBar from "../components/NavigationSideBar";
import LoginScreen from "../screens/LoginScreen";
import MemberSchedule from "./MemberSchedule";
import MemberSessions from "./MemberSessions";
import MemberClasses from "./MemberClasses";
import AvailableClasses from "./AvailableClasses";
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
            </Routes>
        </AppContentView>
    )

}


export default AppContent;

const AppContentView = styled.div`
    display: flex;
`