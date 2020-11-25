//Leetcode N46

// Given an array nums of distinct integers, return all the possible permutations.
// You can return the answer in any order.

/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
  let permutations = []; 
  
  const backtrack = (currentCombination, candidates) => {
    if (currentCombination.length == nums.length) {
      permutations.push([...currentCombination]); 
      return; 
    }
    for (var i = 0; i < candidates.length; i++) {
      currentCombination.push(candidates[i]); 
      let nextCandidates = [...candidates]; 
      nextCandidates.splice(i, 1); 
      backtrack(currentCombination, nextCandidates); 
      currentCombination.pop(); 
    }
  }; 
  backtrack([], nums); 
  return permutations; 
};