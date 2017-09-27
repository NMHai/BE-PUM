0x00401000:	movl %eax, 0x402008
0x00401005:	movl %esi, $0x4026b8<UINT32>
0x0040100a:	pushl $0x79<UINT8>
0x0040100c:	popl %ecx
0x0040100d:	xorl (%esi), %eax
0x0040100f:	addl %esi, $0x4<UINT8>
0x00401012:	rorl %eax
0x00401014:	loop 0x0040100d
0x00401016:	subl %ebx, %ebx
0x00401018:	movb %bl, $0xffffffc0<UINT8>
0x0040101a:	movl %eax, %fs:(%ebx)
0x0040101d:	testl %eax, %eax
0x0040101f:	je 0x0040102a
0x0040102a:	pushl $0x8<UINT8>
0x0040102c:	popl %eax
0x0040102d:	movl %ebx, %fs:(%esi)
0x00401031:	xorb (%eax), %al
0x00401033:	xchgb 0x2(%ebx), %al
0x00401036:	subl 0x402000, %eax
0x0040103c:	movl %ebx, 0xc(%ebx)
0x0040103f:	movl %ebx, 0x14(%ebx)
0x00401042:	movl %ebx, (%ebx)
0x00401044:	movl %ebx, (%ebx)
0x00401046:	movl %ebx, 0x10(%ebx)
0x00401049:	movl %ebp, %ebx
0x0040104b:	addl %ebx, 0x3c(%ebx)
0x0040104e:	movl %ebx, 0x78(%ebx)
0x00401051:	addl %ebx, %ebp
0x00401053:	movl %edx, %ebx
0x00401055:	movl %esi, 0x20(%ebx)
0x00401058:	addl %esi, %ebp
0x0040105a:	subl %ebx, %ebx
0x0040105c:	cld
0x0040105d:	incl %ebx
0x0040105e:	lodsl %eax, %ds:(%esi)
0x0040105f:	addl %eax, %ebp
0x00401061:	pushl %esi
0x00401062:	movl %esi, %eax
0x00401064:	movl %edi, $0x40270b<UINT32>
0x00401069:	pushl $0xe<UINT8>
0x0040106b:	popl %ecx
0x0040106c:	rep cmpsb %es:(%edi), %ds:(%esi)
0x0040106e:	popl %esi
0x0040106f:	jne -20
0x00401071:	decl %ebx
0x00401072:	movl %eax, %edx
0x00401074:	movl %eax, 0x24(%eax)
0x00401077:	addl %eax, %ebp
0x00401079:	movzwl %eax, (%eax,%ebx,2)
0x0040107d:	movl %ebx, %edx
0x0040107f:	movl %ebx, 0x1c(%ebx)
0x00401082:	addl %ebx, %ebp
0x00401084:	movl %ebx, (%ebx,%eax,4)
0x00401087:	addl %ebx, %ebp
0x00401089:	movl %edi, $0x4026fa<UINT32>
0x0040108e:	pushl %edi
0x0040108f:	pushl %ebp
0x00401090:	call 0x00080000
Unknown Node: Unknown Node	
