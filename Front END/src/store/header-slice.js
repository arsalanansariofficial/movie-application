import {createSlice} from "@reduxjs/toolkit";

const headerSlice = createSlice({
    name: 'headerSlice',
    initialState: {
        displayButton: false
    },
    reducers: {
        setDisplayButton(state, action) {
            state.displayButton = action.payload;
        }
    }
});

export const headerActions = headerSlice.actions;
export default headerSlice.reducer;
