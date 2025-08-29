import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { customFetch } from "../../utils";

export const fetchBudget = createAsyncThunk(
  "budget/fetchBudget",
  async (userId, thunkAPI) => {
    try {
      const response = await customFetch.get(`/budget/${userId}`, {
        headers: { "Content-Type": "application/json" },
      });

      return response.data.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(
        error?.response?.data?.message || "Failed to fetch budget"
      );
    }
  }
);

export const updateBudget = createAsyncThunk(
  "budget/updateBudget",
  async ({ budgetId, data }, thunkAPI) => {
    try {
      const response = await customFetch.put(`/budget/${budgetId}`, data, {
        headers: { "Content-Type": "application/json" },
      });

      return response.data.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(
        error?.response?.data?.message || "Failed to update budget"
      );
    }
  }
);

const budgetSlice = createSlice({
  name: "budget",
  initialState: {
    budget: null,
    loading: false,
    error: null,
  },
  reducers: {
    clearBudget: (state) => {
      state.budget = null;
      state.error = null;
      state.loading = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchBudget.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchBudget.fulfilled, (state, action) => {
        state.loading = false;
        state.budget = action.payload;
      })
      .addCase(fetchBudget.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(updateBudget.fulfilled, (state, action) => {
        state.budget = action.payload;
      });
  },
});

export const { clearBudget } = budgetSlice.actions;
export default budgetSlice.reducer;
