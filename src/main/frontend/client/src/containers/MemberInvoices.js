import React, { useState, useEffect, useCallback } from "react";
import styled from "styled-components";
import { getMemberUnpaidInvoices, 
        getMemberPaidInvoices, 
        getMemberCancelledInvoices, 
        getMemberProcessingInvoices,
        payMemberInvoice } from "../api/memberApi";
import { useAuth } from "../contexts/AuthContext";

const MemberInvoices = () => {
    const [currentType, setCurrentType] = useState("unpaid"); // Default to "paid"
    const [invoices, setInvoices] = useState([]);
    const { userId } = useAuth();

    // Function to load invoices based on type
    const loadInvoices = useCallback(async (type) => {
        if (!userId) return;

        let fetchedInvoices;
        switch (type) {
            case "paid":
                fetchedInvoices = await getMemberPaidInvoices(userId);
                break;
            case "unpaid":
                fetchedInvoices = await getMemberUnpaidInvoices(userId);
                break;
            case "processing":
                fetchedInvoices = await getMemberProcessingInvoices(userId);
                break;
            case "cancelled":
                fetchedInvoices = await getMemberCancelledInvoices(userId);
                break;
            default:
                fetchedInvoices = [];
        }
        console.log('return invoices', fetchedInvoices);
        setInvoices(fetchedInvoices);
    }, [userId]);

    const changeInvoiceType = (type) => {
        setCurrentType(type);
        loadInvoices(type);
    };

    useEffect(() => {
        loadInvoices(currentType);
    }, [currentType, userId, loadInvoices]);

    const payInvoice = useCallback(async (paymentId) => {
        console.log('pay invoice: ', paymentId)
        try {
            const res = await payMemberInvoice(paymentId);
            if (res === "Success") {
                loadInvoices(currentType);
            } else {
                window.alert("Unable to pay invoice");
            }
        } catch {
            window.alert("Unable to pay invoice");
        }
    }, [currentType, loadInvoices])

    return (
        <Container>
            <TabBar>
                <Tab onClick={() => changeInvoiceType("processing")}>Processing</Tab>
                <Tab onClick={() => changeInvoiceType("paid")}>Paid</Tab>
                <Tab onClick={() => changeInvoiceType("unpaid")}>Unpaid</Tab>
                <Tab onClick={() => changeInvoiceType("cancelled")}>Cancelled</Tab>
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
                    {currentType !== "cancelled" && <div>Date</div>}
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
                            {currentType !== "cancelled" && <div>{invoice.date}</div>}
                        </InvoiceItem>
                        <InvoiceItem>
                        {currentType === "unpaid" && 
                        <PayInvoiceButton onClick={() => payInvoice(invoice.paymentId)}>Pay Invoice</PayInvoiceButton>}
                        </InvoiceItem>
                    </InvoiceRow>
                ))}
            </InvoiceList>
        </Container>
    );
};

export default MemberInvoices;

// Styled components
const Container = styled.div``;

const TabBar = styled.div``;

const Tab = styled.button`
    padding: 10px 10px;
    background-color: #007bff; 
    width: 150px;
    align-self: flex-end;
    color: white;
    cursor: pointer;
    font-size: 14px;

    &:hover {
        background-color: orange; 
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

const PayInvoiceButton = styled.button`
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