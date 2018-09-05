function string:split(sep)
   local sep, fields = sep or "|", {}
   local pattern = string.format("([^%s]+)", sep)
   self:gsub(pattern, function(c) fields[#fields+1] = c end)
   return fields
end

local function equal(expected, actual)
  if expected == nil or expected == "" then
    return true
  end
  return expected == actual
end

local function greaterThanOrEqual(arg1, arg2)
  if arg1 == nil or arg2 == nil then
    return true
  end
  
  return arg1>=arg2
end

local key = KEYS[1]
local expectedMsgKey= ARGV[1] 
local expectedMsgGroupKey=ARGV[2]
local expectedChannelKey = ARGV[3] 
local expectedRespKey = ARGV[4]
local expectedDays=tonumber(ARGV[5])
local expectedCount=tonumber(ARGV[6])

local list = redis.call('lrange', key ,0,-1)

if (list == nil) then
     return true
end

local msgKeyIndex=1 
local channelKeyIndex=2  
local respKeyIndex=3  
local msgGroupKeyIndex=4  
local countIndex=5  
local daysIndex=6


local sum = 0
for i,element in ipairs(list) do
	local fields = element:split()
	if equal(expectedMsgKey,fields[msgKeyIndex]) and equal(expectedChannelKey ,fields[channelKeyIndex])
	  and equal(expectedRespKey ,fields[respKeyIndex]) 
	  and equal(expectedMsgGroupKey,fields[msgGroupKeyIndex])
	  and greaterThanOrEqual(expectedDays, tonumber(fields[daysIndex])) 
	  then
	  sum = sum + tonumber(fields[countIndex])
	  if expectedCount ~= nil and sum > expectedCount then
	    return tostring(sum)
	  end  
	end
end
return tostring(sum) 