import React, { useState, useEffect, useCallback } from "react";
import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import { getAllInvoices, getProcessingInvoices, processInvoice } from "../api/adminApi";

const AdminInvoiceContainer = () => {
    const [currentType, setCurrentType] = useState("all"); 
    const [invoices, setInvoices] = useState([]);
    const { userType } = useAuth();

    // Function to load invoices based on type
    const loadInvoices = useCallback(async (type) => {
        if (!userType === "Admin") return;

        let fetchedInvoices;
        switch (type) {
            case "all":
                fetchedInvoices = await getAllInvoices();
                break;
            case "processing":
                fetchedInvoices = await getProcessingInvoices();
                break;
            default:
                fetchedInvoices = [];
        }
        console.log('return invoices', fetchedInvoices);
        setInvoices(fetchedInvoices);
    }, [userType]);

    const changeInvoiceType = (type) => {
        setCurrentType(type);
        loadInvoices(type);
    };

    useEffect(() => {
        loadInvoices(currentType);
    }, [currentType, loadInvoices]);

    const tryProcessInvoice = useCallback(async (paymentId) => {
        console.log('process invoice: ', paymentId)
        try {
            const res = await processInvoice(paymentId);
            if (res === "Success") {
                loadInvoices(currentType);
            } else {
                window.alert("Unable to process invoice");
            }
        } catch {
            window.alert("Unable to process invoice");
        }
    }, [currentType, loadInvoices])

    return (
        <Container>
            <TabBar>
                <Tab onClick={() => changeInvoiceType("all")}>All Invoices</Tab>
                <Tab onClick={() => changeInvoiceType("processing")}>Invoices To Process</Tab>
            </TabBar>
            <Header>{currentType.charAt(0).toUpperCase() + currentType.slice(1)} Invoices</Header>
            <ColumnHeaders>
                <ColumnHeader>
                    Type
                </ColumnHeader>
                <ColumnHeader>
                    Cost
                </ColumnHeader>
                <ColumnHeader>
                    Status
                </ColumnHeader>
                <ColumnHeader>
                    Member ID
                </ColumnHeader>
                <ColumnHeader>
                </ColumnHeader>
            </ColumnHeaders>
            <InvoiceList>
                {invoices.map((invoice, index) => (
                    <InvoiceRow key={invoice.paymentId}>
                        <InvoiceItem >
                            <div>{invoice.type}</div>
                        </InvoiceItem>
                        <InvoiceItem>
                            <div>$ {invoice.cost}</div>
                        </InvoiceItem>
                        <InvoiceItem>
                            <div>{invoice.status}</div>
                        </InvoiceItem>
                        <InvoiceItem>
                            <div>{invoice.memberId}</div>
                        </InvoiceItem>
                        <InvoiceItem>
                        {currentType === "processing" && 
                        <ProcessInvoiceButton onClick={() => tryProcessInvoice(invoice.paymentId)}>Process Invoice</ProcessInvoiceButton>}
                        </InvoiceItem>
                    </InvoiceRow>
                ))}
            </InvoiceList>
        </Container>
    );
};

export default AdminInvoiceContainer;

// Styled components
const Container = styled.div``;

const TabBar = styled.div``;

const Tab = styled.button`
    padding: 10px 10px;
    background-color: #ACACAC; 
    width: 300px;
    align-self: flex-end;
    color: white;
    cursor: pointer;
    font-size: 18px;

    &:hover {
        background-color: #666666; 
    }
`;

const Header = styled.h2`
    margin-left: 40px;
`;

const InvoiceList = styled.div``;

const InvoiceItem = styled.div`
    width: 200px;

`;

const InvoiceRow = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin: 20px 20px;
    text-align: center;
    font-size: 16px;

`;

const ProcessInvoiceButton = styled.button`
    padding: 5px 10px;
    background-color: green; 
    width: 140px;
    align-self: flex-end;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 14px;
    border-radius: 5px;

    &:hover {
        background-color: orange; 
    }
`;

const ColumnHeader = styled.div`
    font-weight: bold;
    font-size: 18px;
    width: 200px;
    text-align: center;
`;

const ColumnHeaders = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin: 20px 20px;
`