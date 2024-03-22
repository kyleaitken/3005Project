import { API_ENDPOINTS } from './config';
import { get, del, post} from './utils';

export const getMemberSchedule = (memberId) => {
    const url = API_ENDPOINTS.memberSchedule(memberId);
    return get(url);
};

export const getAvailableTrainers = () => {
    const url = API_ENDPOINTS.getAvailableTrainers();
    return get(url);
}

export const getMemberUpcomingSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberUpcomingSessions(memberId);
    return get(url);
}

export const getMemberPastSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberPastSessions(memberId);
    return get(url);
}

export const cancelMemberSession = (sessionId) => {
    const url = API_ENDPOINTS.cancelMemberSession(sessionId);
    return del(url);
}

export const getMemberUpcomingClasses = (memberId) => {
    const url = API_ENDPOINTS.getMemberUpcomingClasses(memberId);
    return get(url);
}

export const getMemberPastClasses = (memberId) => {
    const url = API_ENDPOINTS.getMemberPastClasses(memberId);
    return get(url);
}

export const leaveClass = (memberId, classId) => {
    const url = API_ENDPOINTS.leaveClassById(memberId, classId);
    return del(url);
}

export const addSession = (memberId, trainerId, date, time) => {
    return post(API_ENDPOINTS.addMemberSession(memberId), {trainerId, date, time });
};