local statKey = KEYS[1]
local msgKey= ARGV[1]
local channelKey = ARGV[2]


local stats = redis.call('lrange','stat',0,-1)

if (stats == nil) then
     return false
end

local stat

local sum = 0
for _,element in ipairs(stats) do
	stat = cjson.decode(element)
	if stat["msgKey"] == msgKey and stat["channelKey"] == channelKey then
		sum = sum + tonumber(stat["count"])
	end
end
local expected = tonumber(ARGV[5])
local result = sum < expected
return tostring(sum)

